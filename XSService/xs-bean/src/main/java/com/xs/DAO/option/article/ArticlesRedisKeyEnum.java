package com.xs.DAO.option.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticlesRedisKeyEnum {
    ARTICLES_KEY("articles:"),
    ARTICLES_TITLE_ADD("title:"),
    ARTICLES_AUTHOR_ADD("author:"),
    ARTICLES_TAG_ADD("tag:");
    private final String key;
}
