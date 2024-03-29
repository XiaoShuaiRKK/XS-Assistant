package com.xs.assistant.article.Service;

import com.mongodb.lang.Nullable;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;

import java.util.List;


public interface ArticleService {

    /**
     *
     * @param articleId
     * @return
     */
    ResponseResult<ArticleVO> findArticleByArticleId(@Nullable String articleId);
    ResponseResult<List<ArticleVO>> findArticlesByArticleIds(@Nullable List<String> articleIds);
    ResponseResult<List<ArticleVO>> findArticleByTitle(@Nullable String title,int page, int size);
    ResponseResult<List<ArticleVO>> findArticleBySubTitle(String title,int page,int size);
    ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTilte(String title,int page,int size);
    ResponseResult<Boolean> addArticle(@Nullable ArticleVO article);
}
