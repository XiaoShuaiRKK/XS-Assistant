package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleAmqp {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JsonUtil jsonUtil;

    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE = "articleChange";
    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE = "articleHotValueExchange";

    public void uploadArticle(ArticleContext article){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE,"article.single",jsonUtil.beanToJson(article));
    }

    public void insertHotArticle(String articleId){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE,"article.hot.value.default",articleId);
    }
}
