package com.xs.assistant.search.Service;

import com.xs.DAO.DO.article.ArticleContext;

import java.util.List;

public interface ESSearchService {
    List<ArticleContext> searchArticlesAll();
    List<ArticleContext> searchArticlesAll(int page, int size);
    List<ArticleContext> searchArticlesQuery(String field, String target, int page, int size);
    List<ArticleContext> searchArticlesAllQuery(String target, int page, int size);
    List<ArticleContext> searchArticlesScoreQuery(String field, String target, int page, int size);
}
