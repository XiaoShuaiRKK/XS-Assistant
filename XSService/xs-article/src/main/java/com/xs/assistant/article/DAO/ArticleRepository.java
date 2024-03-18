package com.xs.assistant.article.DAO;

import com.xs.DAO.DO.article.ArticleMongoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<ArticleMongoDO,String> {
    Page<ArticleMongoDO> findByTitle(String title, Pageable pageable);
    Page<ArticleMongoDO> findAllByTitleLike(String title,Pageable pageable);
    Page<ArticleMongoDO> findAllByTitleLikeOrSubTitleLike(String title,Pageable pageable);
}
