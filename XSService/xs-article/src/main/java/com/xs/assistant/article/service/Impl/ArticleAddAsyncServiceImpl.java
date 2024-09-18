package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.service.ArticleAddAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class ArticleAddAsyncServiceImpl implements ArticleAddAsyncService {
    final ArticleAmqp articleAmqp;
    final ArticleMongodbRepository articleRepository;
    final ArticleDAO articleDAO;

    public ArticleAddAsyncServiceImpl(ArticleAmqp articleAmqp, ArticleMongodbRepository articleRepository, ArticleDAO articleDAO) {
        this.articleAmqp = articleAmqp;
        this.articleRepository = articleRepository;
        this.articleDAO = articleDAO;
    }

    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> insertArticleMysql(ArticleContext article){
        //mysql
        return AsyncResult.forValue(articleDAO.insertArticle(ArticleFactory.defaultArticle(article.getAuthorId(),article.getId())) > 0);
    }

    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> insertArticleMongo(ArticleContext article){
        //mongodb
        articleRepository.insert(article);
        //利用消息队列来进行操作告知
        addArticleAmqp(article);
        return AsyncResult.forValue(true);
    }

    private void addArticleAmqp(ArticleContext article){
        //添加文章
        articleAmqp.uploadArticle(article);
        //添加文章对应的热度表
        articleAmqp.insertHotArticle(article.getId());
    }
}
