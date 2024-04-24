package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.Factory.ArticleFactory;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleRepository;
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
    ArticleRepository articleRepository;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    ArticleAmqp articleAmqp;

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "failMethod")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addArticle(ArticleMongoDO article) {
        try {
            String articleId = codeUtil.createCodeWithArticle(articleRepository.count());
            if(articleId == null)
                throw new RuntimeException();
            article.setId(articleId);
            articleRepository.insert(article);
            articleAmqp.uploadArticle(article);
            int rs = articleDAO.insertArticle(ArticleFactory.defaultArticle(article.getAuthorId(),articleId));
            if(rs <= 0)
                throw new RuntimeException();
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
