package com.xs.assistant.hot.DAO;

public class ArticleHotKey {
    private ArticleHotKey(){}
    public static final String ES_ARTICLE_INDEX_NAME = "article";
    public static final String ES_ARTICLE_HOT_COLUMN = "hot";
    public static final String RABBITMQ_EXCHANGE_NAME = "articleHotExchange";
    public static final String RABBITMQ_EXCHANGE_KEY_COLUMN = "article.hot.column";
    public static final String REDIS_ARTICLE_HOT_KEY = "articleHot:";
}
