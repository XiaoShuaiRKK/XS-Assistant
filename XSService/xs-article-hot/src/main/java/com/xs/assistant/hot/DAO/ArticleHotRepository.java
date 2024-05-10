package com.xs.assistant.hot.DAO;

import com.xs.DAO.DO.article.ArticleHot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleHotRepository extends ElasticsearchRepository<ArticleHot,String> {
}
