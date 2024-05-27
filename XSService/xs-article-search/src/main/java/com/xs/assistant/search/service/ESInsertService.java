package com.xs.assistant.search.service;

import com.xs.DAO.DO.article.ArticleContext;

import java.util.List;

public interface ESInsertService {
    boolean insert(List<ArticleContext> articles);
    boolean insert(ArticleContext article);
}
