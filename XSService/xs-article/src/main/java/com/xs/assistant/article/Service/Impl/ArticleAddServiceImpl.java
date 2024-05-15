package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.DAO.ArticleSearchRepository;
import com.xs.assistant.article.Service.ArticleAddService;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    final ArticleMongodbRepository articleRepository;
    final ArticleDAO articleDAO;
    final ArticleSearchRepository articleSearchRepository;
    final ArticleAmqp articleAmqp;
    final SnowflakeDistributeId snowflakeDistributeId;

    public ArticleAddServiceImpl(UIDCodeUtil codeUtil, ArticleMongodbRepository articleRepository,
                                 ArticleDAO articleDAO, ArticleSearchRepository articleSearchRepository,
                                 ArticleAmqp articleAmqp) {
        this.codeUtil = codeUtil;
        this.articleRepository = articleRepository;
        this.articleDAO = articleDAO;
        this.articleSearchRepository = articleSearchRepository;
        this.articleAmqp = articleAmqp;
        this.snowflakeDistributeId = new SnowflakeDistributeId(0,0);
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addArticle(ArticleContext article) {
        try {
            //根据雪花算法生成唯一ID
            String articleId = codeUtil.createCodeWithArticle(snowflakeDistributeId.nextId(),MYSQL_DEFAULT_ID_SIZE);
            article.setId(articleId);
            article.setHot(0D);
            article.setStateId(1);
            //mysql
            int rs = articleDAO.insertArticle(ArticleFactory.defaultArticle(article.getAuthorId(),articleId));
            if(rs <= 0)
                throw new RuntimeException();
            //mongodb
            articleRepository.insert(article);
            articleAmqp.uploadArticle(article);
            articleAmqp.insertHotArticle(articleId);
            return ResponseResult.success(true);
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    private <T> ResponseResult<T> failMethod(Exception e){
        return ResponseResult.error(null,"操作失败,请稍后重试");
    }

}
