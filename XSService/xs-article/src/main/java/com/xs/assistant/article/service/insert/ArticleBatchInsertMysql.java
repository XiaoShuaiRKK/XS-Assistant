package com.xs.assistant.article.service.insert;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import com.xs.assistant.article.DAO.ArticleBatchRepository;
import com.xs.assistant.article.DAO.ArticleDAO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service("articleBatchInsertMysql")
public class ArticleBatchInsertMysql implements ArticleInsert,ArticleBatchInsert {
    final ArticleDAO articleDAO;

    public ArticleBatchInsertMysql(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    @Deprecated
    public Future<Boolean> insertArticle(ArticleContext article) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    @Async("articleInsertAsyncExecutor")
    public Future<Boolean> batchInsert(List<ArticleContext> articles) {
        List<Article> articleList = new ArrayList<>();
        articles.forEach(article -> articleList.add(ArticleFactory.defaultArticle(article.getAuthorId(),article.getId())));
        boolean rs = articleDAO.insertArticleBatch(articleList) > 0;
        return new AsyncResult<>(rs);
    }
}
