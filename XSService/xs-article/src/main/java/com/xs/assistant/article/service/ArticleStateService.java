package com.xs.assistant.article.service;

import com.xs.DAO.DO.article.ArticleState;

import java.util.List;

public interface ArticleStateService {
    List<ArticleState> findAll();
    ArticleState findById(Integer id);
}
