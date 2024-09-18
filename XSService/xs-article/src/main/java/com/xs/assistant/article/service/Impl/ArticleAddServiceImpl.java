package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.DAO.ArticleSearchRepository;
import com.xs.assistant.article.service.ArticleAddAsyncService;
import com.xs.assistant.article.service.ArticleAddService;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    final ArticleAddAsyncService addAsyncService;

    public ArticleAddServiceImpl(UIDCodeUtil codeUtil, ArticleSearchRepository articleSearchRepository, ArticleAddAsyncService addAsyncService) {
        this.codeUtil = codeUtil;
        this.articleSearchRepository = articleSearchRepository;
        this.addAsyncService = addAsyncService;
        this.snowflakeDistributeId = new SnowflakeDistributeId(0,0);
    }

    /**
     * 添加文章
     * @param article 文章
     * @return 是否成功
     */
    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    @Async("articleAsyncExecutor")
    public Future<Boolean> addArticle(ArticleContext article) {
        try {
            //根据雪花算法生成唯一ID
            String articleId = codeUtil.createCode(UIDCodeUtil.CreateCodeType.ARTICLE,
                    snowflakeDistributeId.nextId(),MYSQL_DEFAULT_ID_SIZE);
            article.setId(articleId);
            article.setHot(0D);
            article.setStateId(1);
            Future<Boolean> rsMysql = addAsyncService.insertArticleMysql(article);
            Future<Boolean> rsMongo = addAsyncService.insertArticleMongo(article);
            return AsyncResult.forValue(rsMysql.get() && rsMongo.get());
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AsyncResult.forValue(false);
        }
    }



    private  Future<Boolean> failMethod(Exception e){
        return AsyncResult.forValue(false);
    }

}
