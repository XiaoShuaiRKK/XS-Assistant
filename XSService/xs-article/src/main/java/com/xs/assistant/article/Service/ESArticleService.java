package com.xs.assistant.article.Service;


import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;

import java.util.List;

public interface ESArticleService {
    ResponseResult<List<ArticleContextVO>> getArticlesAll();
    ResponseResult<List<ArticleContextVO>> getArticlesByAllField(String target, int page, int size);
    ResponseResult<List<ArticleContextVO>> getArticlesByScore(String field, String target, int page, int size);
}
