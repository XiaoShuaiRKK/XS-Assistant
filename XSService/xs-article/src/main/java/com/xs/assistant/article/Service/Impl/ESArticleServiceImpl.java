package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.ResponseResult;
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
    public ResponseResult<List<ArticleMongoDO>> getArticlesAll() {
        List<ArticleMongoDO> articles = esArticleRemoteService.getArticlesAll();
        return articles.isEmpty() ? ResponseResult.error("获取失败") : ResponseResult.success(articles);
    }
}
