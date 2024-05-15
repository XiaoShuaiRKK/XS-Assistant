package com.xs.DAO.mapper;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.VO.article.ArticleContextVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * mapstruct
 * 来进行实体类的转换
 */
@Mapper
public interface ArticleContextMapper {
    ArticleContextMapper INSTANCE = Mappers.getMapper(ArticleContextMapper.class);
    @Mappings({
            @Mapping(source = "articleContext.id",target = "id"),
            @Mapping(source = "article.authorId",target = "authorId",defaultValue = "XS0"),
            @Mapping(source = "article.image",target = "image",defaultValue = "Illustration 1"),
            @Mapping(source = "article.background",target = "background",defaultValue = "Background 1"),
            @Mapping(source = "article.stateId",target = "stateId",defaultValue = "1"),
            @Mapping(source = "articleContext.description",target = "description",defaultValue = "This good article")
    })
    ArticleContextVO articleToArticleContextVO(ArticleContext articleContext, Article article);
}
