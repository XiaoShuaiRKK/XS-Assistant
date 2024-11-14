package com.xs.DAO.factory;

import com.xs.DAO.DO.article.ArticleExamine;
import com.xs.DAO.VO.article.ArticleExamineVO;

import java.sql.Date;

public class ArticleExamineFactory {
    public static ArticleExamine createArticleExamine(String articleId,int examineState,String description) {
        Date now = new Date(System.currentTimeMillis());
        return new ArticleExamine(0,articleId,examineState,description,now,now);
    }

    public static ArticleExamineVO createArticleExamineVO(ArticleExamine articleExamine,String examineName) {
        return new ArticleExamineVO(
                articleExamine.getId(),
                articleExamine.getArticleId(),
                articleExamine.getExamineState(),
                examineName,
                articleExamine.getDescription(),
                articleExamine.getCreateTime(),
                articleExamine.getUpdateTime()
        );
    }
}
