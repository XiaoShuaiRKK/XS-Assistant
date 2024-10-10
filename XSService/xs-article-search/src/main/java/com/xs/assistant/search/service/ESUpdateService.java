package com.xs.assistant.search.service;

import com.xs.DAO.DO.article.ArticleContext;

public interface ESUpdateService {
    boolean deleteArticle(String id);
    boolean updateArticle(ArticleContext context);
}
