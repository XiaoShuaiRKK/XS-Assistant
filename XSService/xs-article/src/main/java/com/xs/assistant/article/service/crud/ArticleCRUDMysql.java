package com.xs.assistant.article.service.crud;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.assistant.article.DAO.ArticleDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service("articleCRUDMysql")
@Slf4j
public class ArticleCRUDMysql implements CRUDOperate<ArticleContext> {

    final ArticleDAO articleDAO;

    public ArticleCRUDMysql(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> insertArticle(ArticleContext article) {
        long startTime = System.currentTimeMillis();
        boolean result = articleDAO.insertArticle(ArticleFactory.defaultArticle(article.getAuthorId(),article.getId())) > 0;
        //mysql
        log.info("Insert Mysql Spend Time:" + (System.currentTimeMillis() - startTime));
        return AsyncResult.forValue(result);
    }

    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> batchInsert(List<ArticleContext> articles) {
        List<Article> articleList = new ArrayList<>();
        articles.forEach(article -> articleList.add(ArticleFactory.defaultArticle(article.getAuthorId(),article.getId())));
        boolean rs = articleDAO.insertArticleBatch(articleList) > 0;
        return new AsyncResult<>(rs);
    }

    @Override
    public Future<Boolean> deleteArticle(ArticleContext entity) {
        return null;
    }

    @Override
    public Future<Boolean> deleteArticles(List<ArticleContext> entities) {
        return null;
    }

    @Override
    public Future<Boolean> updateArticle(ArticleContext entity) {
        return null;
    }

    @Override
    public Future<Boolean> updateArticles(List<ArticleContext> entities) {
        return null;
    }

    @Override
    public Future<Boolean> rollbackInsert(ArticleContext entity) {
        return deleteArticle(entity);
    }

    @Override
    public Future<Boolean> rollbackBatchInsert(List<ArticleContext> entities) {
        return deleteArticles(entities);
    }

    @Override
    public Future<Boolean> rollbackDelete(ArticleContext entity) {
        return null;
    }

    @Override
    public Future<Boolean> rollbackDeletes(List<ArticleContext> entities) {
        return null;
    }

    @Override
    public Future<Boolean> rollbackUpdate(ArticleContext entity) {
        return null;
    }

    @Override
    public Future<Boolean> rollbackUpdates(List<ArticleContext> entities) {
        return null;
    }
}
