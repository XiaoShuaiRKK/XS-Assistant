package com.xs.assistant.search.Service.Impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.search.Service.ESSearchService;
import com.xs.assistant.search.Service.ESService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
public class ESSearchServiceImpl extends ESService implements ESSearchService {

    final static Function<List<Hit<ArticleContext>>,List<ArticleContext>> ToList =
            hits -> hits.parallelStream().map(Hit::source).toList();

    final static Map<String, ArticleContext> ArticlesCache = new ConcurrentHashMap<>();

    @Override
    public List<ArticleContext> searchArticlesAll() {
        return ToList.apply(elasticsearchUtil.searchDocuments(ES_ARTICLE_INDEX_NAME,0,1000, ArticleContext.class));
    }

    @Override
    public List<ArticleContext> searchArticlesAll(int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocuments(ES_ARTICLE_INDEX_NAME,page,size, ArticleContext.class));
    }

    @Override
    public List<ArticleContext> searchArticlesQuery(String field, String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsQuery(ES_ARTICLE_INDEX_NAME,field,target,page,size, ArticleContext.class));
    }

    @Override
    public List<ArticleContext> searchArticlesAllQuery(String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsMultiQuery(ES_ARTICLE_INDEX_NAME,
                target,page,size, ArticleContext.class));
    }

    @Override
    public List<ArticleContext> searchArticlesScoreQuery(String field, String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsMaxScore(ES_ARTICLE_INDEX_NAME,field,target,
                page,size, ArticleContext.class));
    }

}
