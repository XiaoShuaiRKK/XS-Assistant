package com.xs.assistant.article.Service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;

public interface ArticleAddService {
    ResponseResult<Boolean> addArticle(@Nullable ArticleMongoDO article);
}
