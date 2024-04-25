package com.xs.assistant.article.Service;


import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;

import java.util.List;

public interface ESArticleService {
    ResponseResult<List<ArticleContext>> getArticlesAll();
    ResponseResult<List<ArticleContext>> getArticlesByAllField(String target, int page, int size);
    ResponseResult<List<ArticleContext>> getArticlesByScore(String field, String target, int page, int size);
}
