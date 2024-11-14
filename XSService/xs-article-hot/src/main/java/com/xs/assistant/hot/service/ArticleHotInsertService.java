package com.xs.assistant.hot.service;

import com.xs.DAO.DO.article.ArticleHot;

import java.util.List;

public interface ArticleHotInsertService {
    Boolean insertDefaultHot(String articleId);
    Boolean batchInsertDefaultHot(List<String> articleIds);
    Boolean insertHasValueHot(String articleId,Long comment,Long star,Long liked);
    Boolean insertHasValueHot(ArticleHot articleHot);
    Integer deleteHot(String articleId);
}
