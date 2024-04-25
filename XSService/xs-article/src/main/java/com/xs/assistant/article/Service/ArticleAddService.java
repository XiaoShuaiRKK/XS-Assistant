package com.xs.assistant.article.Service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;

public interface ArticleAddService {
    ResponseResult<Boolean> addArticle(@Nullable ArticleContext article);
}
