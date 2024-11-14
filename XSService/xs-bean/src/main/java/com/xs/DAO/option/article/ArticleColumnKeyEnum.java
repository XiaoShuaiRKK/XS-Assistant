package com.xs.DAO.option.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleColumnKeyEnum {
    ARTICLE_COLUMN_KEY_AUTHOR_ID("authorId");
    private final String name;
}
