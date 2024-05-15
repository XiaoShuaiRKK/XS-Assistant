package com.xs.assistant.search.Service.Impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.repository.ElasticRepositoryKeyEnum;
import com.xs.assistant.search.Service.ESSearchService;
import com.xs.assistant.search.Service.ESService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
public class ESSearchServiceImpl extends ESService implements ESSearchService {

    /**
     * 将查询从es查询的数据进行包装
     * 从List<Hit<T>> 变成 List<T>
     */
    static final Function<List<Hit<ArticleContext>>,List<ArticleContext>> ToList =
            hits -> hits.parallelStream().map(Hit::source).toList();

    /**
     * 本地缓存
     */
    static final Map<String, ArticleContext> ArticlesCache = new ConcurrentHashMap<>();

    /**
     * 查询所有 默认只查询分页0，1000
     * 不推荐使用
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesAll() {
        return ToList.apply(elasticsearchUtil.searchDocuments(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                0,1000, ArticleContext.class));
    }

    /**
     * 分页查询
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesAll(int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocuments(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                page,size, ArticleContext.class));
    }

    /**
     * 查询对应字段的对应关键字
     * @param field 字段
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesQuery(String field, String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsQuery(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                field,target,page,size, ArticleContext.class));
    }

    /**
     * 根据关键字查询
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesAllQuery(String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsMultiQuery(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                target,page,size, ArticleContext.class,
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_CONTEXT_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_TITLE_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_SUB_TITLE_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_DESCRIPTION_KEY.getKey()));
    }

    /**
     * 根据关键字的匹配度来进行排序
     * @param field 字段
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesScoreQuery(String field, String target, int page, int size) {
        return ToList.apply(elasticsearchUtil.searchDocumentsMaxScore(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                field, page,size, ArticleContext.class));
    }

    /**
     * 根据热度来进行排序
     * @param page 页数
     * @param size 大小
     * @param target 关键字
     * @return 文章列表
     */
    @Override
    public List<ArticleContext> searchArticlesByTargetOrderByHot(int page, int size, String target) {
        List<Hit<ArticleContext>> hits = elasticsearchUtil.searchDocumentsMultiOrderSortQueryFields(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_HOT_KEY.getKey(), SortOrder.Desc, target, page, size, ArticleContext.class,
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_TITLE_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_SUB_TITLE_KEY.getKey(),
                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_CONTEXT_KEY.getKey());
        return ToList.apply(hits);
    }

}
