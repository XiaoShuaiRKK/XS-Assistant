package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.Aspect.Annotation.ResultPackage;
import com.xs.assistant.article.Service.ESArticleService;
import com.xs.assistant.article.Service.Remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ESArticleServiceImpl implements ESArticleService {
    @Autowired
    ESArticleRemoteService esArticleRemoteService;

    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContext>> getArticlesAll() {
        return ResponseResult.none(esArticleRemoteService.getArticlesAll());
    }

    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContext>> getArticlesByAllField(String target, int page, int size) {
        return ResponseResult.none(esArticleRemoteService.getArticleAllQuery(target,page,size));
    }

    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContext>> getArticlesByScore(String field, String target, int page, int size) {
        return ResponseResult.none(esArticleRemoteService.getArticleScoreQuery(field,target,page,size));
    }
}
