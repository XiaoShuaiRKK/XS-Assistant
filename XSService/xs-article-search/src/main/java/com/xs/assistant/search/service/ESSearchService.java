package com.xs.assistant.search.service;

import com.xs.DAO.DO.article.ArticleContext;

import java.util.List;

public interface ESSearchService {
    List<ArticleContext> searchArticlesAll();
    List<ArticleContext> searchArticlesAll(int page, int size);
    List<ArticleContext> searchArticlesQuery(String field, String target, int page, int size);
    List<ArticleContext> searchArticlesAllQuery(String target, int page, int size);
    List<ArticleContext> searchArticlesScoreQuery(String field, String target, int page, int size);
    List<ArticleContext> searchArticlesByTargetOrderByHot(int page,int size,String target);
    List<ArticleContext> searchArticlesOrderByHot(int page,int size);
}
