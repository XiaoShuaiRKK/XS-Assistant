package com.xs.assistant.search.Service;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.xs.DAO.DO.article.ArticleMongoDO;

import java.util.List;

public interface ESSearchService {
    List<ArticleMongoDO> searchArticlesAll();
    List<ArticleMongoDO> searchArticlesAll(int page,int size);
    List<ArticleMongoDO> searchArticlesQuery(String field,String target,int page,int size);

}
