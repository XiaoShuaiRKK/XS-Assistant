package com.xs.assistant.search.Service;

import com.xs.DAO.DO.article.ArticleMongoDO;

import java.util.List;

public interface ESInsertService {
    boolean insert(List<ArticleMongoDO> articles);
    boolean insert(ArticleMongoDO article);
}
