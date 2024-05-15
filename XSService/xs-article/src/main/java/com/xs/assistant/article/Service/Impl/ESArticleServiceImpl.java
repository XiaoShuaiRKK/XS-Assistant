package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.mapper.ArticleContextMapper;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;
import com.xs.assistant.article.Aspect.Annotation.ResultPackage;
import com.xs.assistant.article.Service.ESArticleService;
import com.xs.assistant.article.Service.Remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseResult<List<ArticleContextVO>> getArticlesByPage(int page,int size) {
        return ResponseResult.none(esArticleRemoteService.getArticlesByPage(page,size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a,new Article())).toList());
    }

    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContextVO>> getArticlesByAllField(String target, int page, int size) {
        return ResponseResult.none(esArticleRemoteService.getArticleAllQuery(target,page,size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a,new Article())).toList());
    }

    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContextVO>> getArticlesByScore(String field, String target, int page, int size) {
        return ResponseResult.none(esArticleRemoteService.getArticleScoreQuery(field,target,page,size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a,new Article())).toList());
    }
}
