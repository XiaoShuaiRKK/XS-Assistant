package com.xs.assistant.search.service;

import com.xs.DAO.DO.article.ArticleContext;

import java.util.List;

public interface ESSearchService {
    List<ArticleContext> searchArticlesAll();
    List<ArticleContext> searchArticlesAll(int from, int size);
    List<ArticleContext> searchArticlesByIdNumber(String idNumber,int from,int size);
    List<ArticleContext> searchArticlesQuery(String field, String target, int from, int size);
    List<ArticleContext> searchArticlesAllQuery(String target, int from, int size);
    List<ArticleContext> searchArticlesScoreQuery(String field, String target, int from, int size);
    List<ArticleContext> searchArticlesByTargetOrderByHot(int from,int size,String target);
    List<ArticleContext> searchArticlesOrderByHot(int from,int size);
}
