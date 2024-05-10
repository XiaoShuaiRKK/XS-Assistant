package com.xs.assistant.article.DAO;

import com.xs.DAO.DO.article.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDAO {
    Integer insertArticle(@Param("article")Article article);
    Long count();
}
