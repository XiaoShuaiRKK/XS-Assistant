package com.xs.assistant.article.DAO;

import com.xs.DAO.DO.article.ArticleContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleMongodbRepository extends MongoRepository<ArticleContext,String> {
    Page<ArticleContext> findByTitle(String title, Pageable pageable);
    Page<ArticleContext> findAllByTitleLike(String title, Pageable pageable);
    Page<ArticleContext> findAllBySubTitleLike(String title, Pageable pageable);
    @Query("{'title': {$regex : ?0, $options: 'i'}, 'subTitle': {$regex : ?0, $options: 'i'}}")
    Page<ArticleContext> findByTitleOrSubTitleContainingIgnoreCase(String title, Pageable pageable);

}
