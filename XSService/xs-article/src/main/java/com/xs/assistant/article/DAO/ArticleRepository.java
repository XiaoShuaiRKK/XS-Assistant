package com.xs.assistant.article.DAO;

import com.mongodb.lang.NonNullApi;
import com.xs.DAO.article.DO.ArticleMongoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<ArticleMongoDO,String> {
    Page<ArticleMongoDO> findByTitle(String title, Pageable pageable);
    Page<ArticleMongoDO> findAllByTitleLike(String title,Pageable pageable);
}
