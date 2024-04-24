package com.xs.assistant.search.Service.Impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.search.Service.ESSearchService;
import com.xs.assistant.search.Service.ESService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESSearchServiceImpl extends ESService implements ESSearchService {

    @Override
    public List<ArticleMongoDO> searchArticlesAll() {
        return elasticsearchUtil.searchDocuments(ES_ARTICLE_INDEX_NAME,0,1000, ArticleMongoDO.class)
                .parallelStream().map(Hit::source).toList();
    }

    @Override
    public List<ArticleMongoDO> searchArticlesAll(int page, int size) {
        return List.of();
    }

    @Override
    public List<ArticleMongoDO> searchArticlesQuery(String field, String target, int page, int size) {
        return List.of();
    }
}
