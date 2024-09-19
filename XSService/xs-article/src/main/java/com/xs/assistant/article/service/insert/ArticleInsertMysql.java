package com.xs.assistant.article.service.insert;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.assistant.article.DAO.ArticleDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("articleInsertMysql")
@Slf4j
public class ArticleInsertMysql implements ArticleInsert {

    final ArticleDAO articleDAO;

    public ArticleInsertMysql(ArticleDAO articleDAO) {
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
}
