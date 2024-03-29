package com.xs.assistant.article.DAO;

import com.xs.DAO.DO.article.ArticleMongoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<ArticleMongoDO,String> {
    Page<ArticleMongoDO> findByTitle(String title, Pageable pageable);
    Page<ArticleMongoDO> findAllByTitleLike(String title,Pageable pageable);
    Page<ArticleMongoDO> findAllBySubTitleLike(String title,Pageable pageable);
    @Query("{'title': {$regex : ?0, $options: 'i'}, 'subTitle': {$regex : ?0, $options: 'i'}}")
    Page<ArticleMongoDO> findByTitleOrSubTitleContainingIgnoreCase(String title,Pageable pageable);

}
