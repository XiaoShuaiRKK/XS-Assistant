package com.xs.assistant.search.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.repository.ElasticRepositoryKeyEnum;
import com.xs.assistant.search.service.ESInsertService;
import com.xs.assistant.search.service.ESService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESInsertServiceImpl extends ESService implements ESInsertService {

    /**
     * 将文章以List的形式添加到elasticsearch
     * 在数据量多的情况下 性能比单个添加好
     * @param articles 文章
     * @return 是否成功
     */
    @Override
    public boolean insert(List<ArticleContext> articles) {
        //将文章的每个article id 提取成list 方便为doc定义id
        List<String> ids = articles.stream().map(ArticleContext::getId).toList();
        return elasticsearchUtil.insertDocuments(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),ids,articles);
    }

    /**
     * 添加单个文章到elasticsearch
     * @param article 文章
     * @return 是否成功
     */
    @Override
    public boolean insert(ArticleContext article) {
        return elasticsearchUtil.insertDocument(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(), article.getId(), article);
    }
}
