package com.xs.assistant.search.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.repository.ElasticRepositoryKeyEnum;
import com.xs.assistant.search.service.ESService;
import com.xs.assistant.search.service.ESUpdateService;
import org.springframework.stereotype.Service;

@Service
public class ESUpdateServiceImpl extends ESService implements ESUpdateService {
    @Override
    public boolean deleteArticle(String id) {
        return elasticsearchUtil.deleteDocument(ElasticRepositoryKeyEnum.ES_ARTICLE_KEY.getKey(),id);
    }

    @Override
    public boolean updateArticle(ArticleContext context) {
        return false;
    }
}
