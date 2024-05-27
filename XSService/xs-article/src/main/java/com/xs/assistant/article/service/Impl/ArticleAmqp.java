package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ArticleAmqp {
    private final RabbitTemplate rabbitTemplate;
    private final JsonUtil jsonUtil;

    /**
     * 添加文章所使用的交换机
     */
    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE = "articleChange";
    /**
     * 添加文章热度所使用的交换机
     */
    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE = "articleHotValueExchange";

    public ArticleAmqp(RabbitTemplate rabbitTemplate, JsonUtil jsonUtil) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonUtil = jsonUtil;
    }

    /**
     * 添加文章
     * 向指定的交换机发送消息
     * @param article 文章
     */
    public void uploadArticle(ArticleContext article){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE,"article.single",jsonUtil.beanToJson(article));
    }

    /**
     * 添加文章对应的热度信息
     * @param articleId 文章id
     */
    public void insertHotArticle(String articleId){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE,"article.hot.value.default",articleId);
    }
}
