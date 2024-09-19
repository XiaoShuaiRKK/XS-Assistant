package com.xs.assistant.article.service.insert;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;

import java.util.concurrent.Future;

public interface ArticleInsert {
    Future<Boolean> insertArticle(ArticleContext article);
}
