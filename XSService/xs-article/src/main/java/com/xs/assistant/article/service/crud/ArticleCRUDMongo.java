package com.xs.assistant.article.service.crud;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service("articleCRUDMongo")
@Slf4j
public class ArticleCRUDMongo implements CRUDOperate<ArticleContext> {


    final ArticleMongodbRepository articleRepository;

    public ArticleCRUDMongo(ArticleMongodbRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> insertArticle(ArticleContext article) {
        long startTime = System.currentTimeMillis();
        //mongodb
        articleRepository.insert(article);
        log.info("Insert Mongo Spend Time:" + (System.currentTimeMillis() - startTime));
        return AsyncResult.forValue(true);
    }

    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> batchInsert(List<ArticleContext> articles) {
        long startTime = System.currentTimeMillis();
        articleRepository.insert(articles);
        log.info("====Mongo Article Batch Insert : " + (System.currentTimeMillis() - startTime) + " ===");
        return AsyncResult.forValue(true);
    }

    @Override
    @Async
    @Retry(name = "mongoRollbackRetry")
    public Future<Boolean> deleteArticle(ArticleContext entity) {
        articleRepository.deleteById(entity.getId());
        return AsyncResult.forValue(true);
    }

    @Override
    @Async
    @Retry(name = "mongoRollbackRetry")
    public Future<Boolean> deleteArticles(List<ArticleContext> entities) {
        List<String> ids = new ArrayList<>();
        entities.forEach(entity -> ids.add(entity.getId()));
        articleRepository.deleteAllById(ids);
        List<ArticleContext> allById = articleRepository.findAllById(ids);
        log.info("Delete Mongo Spend Time:" + (System.currentTimeMillis() - System.currentTimeMillis()));
        log.info("Delete Mongo");
        log.info(allById.toString());
        if(!allById.isEmpty())
            throw new RuntimeException();
        return AsyncResult.forValue(true);
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
