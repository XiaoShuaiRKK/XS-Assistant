package com.xs.assistant.article.service.insert;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.service.Impl.ArticleAmqp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service("articleInsertMongo")
@Slf4j
public class ArticleInsertMongo implements ArticleInsert,ArticleBatchInsert {

    final ArticleAmqp articleAmqp;
    final ArticleMongodbRepository articleRepository;

    public ArticleInsertMongo(ArticleAmqp articleAmqp, ArticleMongodbRepository articleRepository) {
        this.articleAmqp = articleAmqp;
        this.articleRepository = articleRepository;
    }

    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> insertArticle(ArticleContext article) {
        long startTime = System.currentTimeMillis();
        //mongodb
        articleRepository.insert(article);
        //利用消息队列来进行操作告知
        addArticleAmqp(article);
        log.info("Insert Mongo Spend Time:" + (System.currentTimeMillis() - startTime));
        return AsyncResult.forValue(true);
    }

    private void addArticleAmqp(ArticleContext article){
        //添加文章
        articleAmqp.uploadArticle(article);
        //添加文章对应的热度表
        articleAmqp.insertHotArticle(article.getId());
    }

    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> batchInsert(List<ArticleContext> articles) {
        articles.forEach(this::insertArticle);
        return AsyncResult.forValue(true);
    }
}
