package com.xs.assistant.article.service;


import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;

import java.util.List;

public interface ESArticleService {
    ResponseResult<List<ArticleContextVO>> getArticlesByPage(int page,int size);
    ResponseResult<List<ArticleContextVO>> getArticlesByAllField(String target, int page, int size);
    ResponseResult<List<ArticleContextVO>> getArticlesByScore(String field, String target, int page, int size);
    List<ArticleContextVO> getArticlesByTargetFindIdNumber(String idNumber,int page,int size);
}
