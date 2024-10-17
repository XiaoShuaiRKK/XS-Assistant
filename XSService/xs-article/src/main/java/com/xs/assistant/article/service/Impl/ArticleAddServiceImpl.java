package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.option.article.ArticleStateEnum;
import com.xs.assistant.article.DAO.ArticleSearchRepository;
import com.xs.assistant.article.service.ArticleAddService;
import com.xs.assistant.article.service.amqp.ArticleElasticsearchAmqp;
import com.xs.assistant.article.service.amqp.ArticleHotAmqp;
import com.xs.assistant.article.service.crud.CRUDOperate;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class ArticleAddServiceImpl implements ArticleAddService {
    private static final Logger log = LoggerFactory.getLogger(ArticleAddServiceImpl.class);
    /**
     * id默认长度
     * “xsa” + 64 = 67
     * 长度为67
     */
    private static final int MYSQL_DEFAULT_ID_SIZE = 64;
    final UIDCodeUtil codeUtil;
    final ArticleSearchRepository articleSearchRepository;
    final SnowflakeDistributeId snowflakeDistributeId;
    final CRUDOperate<ArticleContext> mongoInsertService;
    final CRUDOperate<ArticleContext> mysqlBatchInsertService;
    final ArticleElasticsearchAmqp articleElasticsearchAmqp;
    final ArticleHotAmqp articleHotAmqp;

    public ArticleAddServiceImpl(UIDCodeUtil codeUtil, ArticleSearchRepository articleSearchRepository,
                                 @Qualifier("articleCRUDMongo") CRUDOperate<ArticleContext> mongoInsertService,
                                 @Qualifier("articleCRUDMysql") CRUDOperate<ArticleContext> mysqlBatchInsertService,
                                 ArticleElasticsearchAmqp articleElasticsearchAmqp,
                                 ArticleHotAmqp articleHotAmqp) {
        this.codeUtil = codeUtil;
        this.articleSearchRepository = articleSearchRepository;
        this.mongoInsertService = mongoInsertService;
        this.mysqlBatchInsertService = mysqlBatchInsertService;
        this.articleElasticsearchAmqp = articleElasticsearchAmqp;
        this.articleHotAmqp = articleHotAmqp;
        this.snowflakeDistributeId = new SnowflakeDistributeId(0, 0);
    }

    /**
     * 添加文章
     * 事务回滚待优化
     *
     * @param article 文章
     * @return 是否成功
     */
    @Override
    @CircuitBreaker(name = "article-mongodb", fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    @Async("articleAsyncExecutor")
    public Future<Boolean> addArticle(ArticleContext article) {
        long startTime = System.currentTimeMillis();
        try {
            //根据雪花算法生成唯一ID
            String articleId = codeUtil.createCode(UIDCodeUtil.CreateCodeType.ARTICLE,
                    snowflakeDistributeId.nextId(), MYSQL_DEFAULT_ID_SIZE);
            article.setId(articleId);
            article.setHot(0D);
            article.setStateId(ArticleStateEnum.NORMAL.ordinal());
            Future<Boolean> rsMysql = mysqlBatchInsertService.insertArticle(article);
            Future<Boolean> rsMongo = mongoInsertService.insertArticle(article);
            //添加文章
            articleElasticsearchAmqp.uploadArticle(article);
            //添加文章对应的热度表
            articleHotAmqp.insertHotArticle(article.getId());
            boolean rs = rsMysql.get() && rsMongo.get();
            if(!rs)
                throw new Exception();
            log.info("Article Add Spend Time: " + (System.currentTimeMillis() - startTime));
            return AsyncResult.forValue(rs);
        } catch (Exception e) {
            log.error(e.getMessage());
            mysqlBatchInsertService.rollbackInsert(article);
            mongoInsertService.rollbackInsert(article);
            articleElasticsearchAmqp.deleteArticle(article.getId());
            articleHotAmqp.deleteHotArticle(article.getId());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AsyncResult.forValue(false);
        }
    }

    /**
     * 事务回滚待优化
     * @param articles
     * @return
     */
    @Override
    @CircuitBreaker(name = "article-mongodb", fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    public Future<Boolean> batchAddArticle(List<ArticleContext> articles) {
        long startTime = System.currentTimeMillis();
        try {
            articles.forEach(article -> {
                String articleId = codeUtil.createCode(UIDCodeUtil.CreateCodeType.ARTICLE,
                        snowflakeDistributeId.nextId(), MYSQL_DEFAULT_ID_SIZE);
                article.setId(articleId);
                article.setHot(0D);
                article.setStateId(1);
            });
            Future<Boolean> rsMysql = mysqlBatchInsertService.batchInsert(articles);
            Future<Boolean> rsMongo = mongoInsertService.batchInsert(articles);
            addArticleAmqp(articles);
            boolean rs = rsMysql.get() && rsMongo.get();
            if (!rs)
                throw new Exception();
            log.info("Article Add Spend Time: " + (System.currentTimeMillis() - startTime));
            return AsyncResult.forValue(rs);
        } catch (Exception e) {
            log.error(e.getMessage());
            mysqlBatchInsertService.rollbackBatchInsert(articles);
            mongoInsertService.rollbackBatchInsert(articles);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AsyncResult.forValue(false);
        }
    }

    @Async("articleAsyncExecutor")
    public void addArticleAmqp(List<ArticleContext> articles) {
        try {
            articles.forEach(article -> {
                articleHotAmqp.deleteHotArticle(article.getId());
                articleElasticsearchAmqp.deleteArticle(article.getId());
            });
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private Future<Boolean> failMethod(Exception e) {
        return AsyncResult.forValue(false);
    }

}
