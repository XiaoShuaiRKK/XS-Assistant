package com.xs.assistant.search.Service.Impl;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.search.Service.ESInsertService;
import com.xs.assistant.search.Service.ESService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESInsertServiceImpl extends ESService implements ESInsertService {

    @Override
    public boolean insert(List<ArticleMongoDO> articles) {
        return elasticsearchUtil.insertDocuments(ES_ARTICLE_INDEX_NAME,articles);
    }

    @Override
    public boolean insert(ArticleMongoDO article) {
        return elasticsearchUtil.insertDocument(ES_ARTICLE_INDEX_NAME, article.getId(), article);
    }
}
