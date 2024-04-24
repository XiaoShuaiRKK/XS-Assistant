package com.xs.assistant.article.Service;


import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.ResponseResult;

import java.util.List;

public interface ESArticleService {
    ResponseResult<List<ArticleMongoDO>> getArticlesAll();
}
