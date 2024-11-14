package com.xs.assistant.article.service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;

import java.util.List;


public interface ArticleService {
    ResponseResult<List<ArticleVO>> getArticles(int page,int size);
    ResponseResult<ArticleVO> findArticleByArticleId(@Nullable String articleId);
    ResponseResult<List<ArticleVO>> findArticlesByArticleIds(@Nullable List<String> articleIds);
    ResponseResult<List<ArticleVO>> findArticleByTitle(@Nullable String title,int page, int size);
    ResponseResult<List<ArticleVO>> findArticleBySubTitle(String title,int page,int size);
    ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTitle(String title,int page,int size);
}
