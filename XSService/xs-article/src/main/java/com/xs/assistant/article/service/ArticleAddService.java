package com.xs.assistant.article.service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;

import java.util.List;
import java.util.concurrent.Future;

public interface ArticleAddService {
    Future<Boolean> addArticle(@Nullable ArticleContext article);
    Future<Boolean> batchAddArticle(@Nullable List<ArticleContext> articles);
}
