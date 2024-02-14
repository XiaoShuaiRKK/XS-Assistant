package com.xs.assistant.article.DAO;

import com.xs.DAO.article.DO.ArticleMongoDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleDAO extends MongoRepository<ArticleMongoDO,String> {
}
