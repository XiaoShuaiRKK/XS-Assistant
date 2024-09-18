package com.xs.assistant.article.service;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.factory.ArticleFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public interface ArticleAddAsyncService {
    Future<Boolean> insertArticleMysql(ArticleContext article);
    Future<Boolean> insertArticleMongo(ArticleContext article);
}
