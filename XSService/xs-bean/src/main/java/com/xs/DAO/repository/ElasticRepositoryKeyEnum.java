package com.xs.DAO.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElasticRepositoryKeyEnum {
    ES_ARTICLE_KEY("article"),
    ES_ARTICLE_COLUMN_HOT_KEY("hot")
    ;
    private final String key;
}
