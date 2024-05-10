package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.DAO.ArticleSearchRepository;
import com.xs.assistant.article.Service.ArticleAddService;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ArticleAddServiceImpl implements ArticleAddService {
    private static final Logger log = LoggerFactory.getLogger(ArticleAddServiceImpl.class);
    @Autowired
    UIDCodeUtil codeUtil;
    @Autowired
    ArticleMongodbRepository articleRepository;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    ArticleSearchRepository articleSearchRepository;
    @Autowired
    ArticleAmqp articleAmqp;

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addArticle(ArticleContext article) {
        try {
            String articleId = codeUtil.createCodeWithArticle(articleDAO.count());
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
