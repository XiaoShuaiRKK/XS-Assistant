package com.xs.DAO.Mapper;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.VO.article.ArticleContextVO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T10:42:30+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ArticleContextMapperImpl implements ArticleContextMapper {

    @Override
    public ArticleContextVO articleContextToArticleContextVO(ArticleContext articleContext, Article article) {
        if ( articleContext == null && article == null ) {
            return null;
        }

        ArticleContextVO.ArticleContextVOBuilder articleContextVO = ArticleContextVO.builder();

        if ( articleContext != null ) {
            articleContextVO.id( articleContext.getId() );
            if ( articleContext.getDescription() != null ) {
                articleContextVO.description( articleContext.getDescription() );
            }
            else {
                articleContextVO.description( "This good article" );
            }
            articleContextVO.context( articleContext.getContext() );
            articleContextVO.title( articleContext.getTitle() );
            articleContextVO.subTitle( articleContext.getSubTitle() );
        }
        if ( article != null ) {
            if ( article.getAuthorId() != null ) {
                articleContextVO.authorId( article.getAuthorId() );
            }
            else {
                articleContextVO.authorId( "XS0" );
            }
            if ( article.getImage() != null ) {
                articleContextVO.image( article.getImage() );
            }
            else {
                articleContextVO.image( "Illustration 1" );
            }
            if ( article.getBackground() != null ) {
                articleContextVO.background( article.getBackground() );
            }
            else {
                articleContextVO.background( "Background 1" );
            }
            if ( article.getStateId() != null ) {
                articleContextVO.stateId( article.getStateId() );
            }
            else {
                articleContextVO.stateId( 1 );
            }
        }

        return articleContextVO.build();
    }
}
