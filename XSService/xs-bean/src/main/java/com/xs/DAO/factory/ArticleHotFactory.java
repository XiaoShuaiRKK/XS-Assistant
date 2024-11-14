package com.xs.DAO.factory;

import com.xs.DAO.DO.article.ArticleHot;
import lombok.Getter;

import java.sql.Date;
import java.util.Optional;

public class ArticleHotFactory {
    @Getter
    public enum ArticleHotKey{
        COMMENT("comment_num"),
        STAR("star_num"),
        LIKED("liked_num");
        private final String columnName;
        ArticleHotKey(String columnName) {
            this.columnName = columnName;
        }
    }
    public static Optional<ArticleHot> getArticleHotSaveValue(ArticleHotKey articleHotKey, Long value){
        switch (articleHotKey){
            case COMMENT -> {
                return Optional.of(ArticleHot.builder().commentNum(value).build());
            }
            case STAR -> {
                return Optional.of(ArticleHot.builder().starNum(value).build());
            }
            case LIKED -> {
                return Optional.of(ArticleHot.builder().likedNum(value).build());
            }
        }
        return Optional.empty();
    }

    public static ArticleHot getDefaultArticleHot(String articleId){
        Date now = new Date(System.currentTimeMillis());
        return ArticleHot.builder().articleId(articleId).createTime(now).updateTime(now).build();
    }
}
