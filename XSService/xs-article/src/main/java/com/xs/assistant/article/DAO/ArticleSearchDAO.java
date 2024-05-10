package com.xs.assistant.article.DAO;

import com.xs.DAO.DO.article.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleSearchDAO {
    Article selectArticleByArticleId(@Param("articleId")String articleId);
    List<Article> selectArticlesByArticleId(@Param("articleIds")List<String> articleIds);
}
