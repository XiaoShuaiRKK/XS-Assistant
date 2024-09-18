package com.xs.assistant.article.service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;

import java.util.concurrent.Future;

public interface ArticleAddService {
    Future<Boolean> addArticle(@Nullable ArticleContext article);
}
