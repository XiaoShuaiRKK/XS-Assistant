package com.xs.DAO.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * elasticsearch
 * index： article
 * 此枚举存储了article在elasticsearch中的信息
 * index 和 column 的信息
 */
@Getter
@AllArgsConstructor
public enum ElasticRepositoryKeyEnum {
    ES_ARTICLE_KEY("article"),
    ES_ARTICLE_COLUMN_HOT_KEY("hot"),
    ES_ARTICLE_COLUMN_ID_KEY("id"),
    ES_ARTICLE_COLUMN_CONTEXT_KEY("context"),
    ES_ARTICLE_COLUMN_TITLE_KEY("title"),
    ES_ARTICLE_COLUMN_SUB_TITLE_KEY("subTitle"),
    ES_ARTICLE_COLUMN_STATE_ID_KEY("stateId"),
    ES_ARTICLE_COLUMN_DESCRIPTION_KEY("description")
    ;
    private final String key;
}
