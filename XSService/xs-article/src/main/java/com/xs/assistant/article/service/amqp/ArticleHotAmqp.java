package com.xs.assistant.article.service.amqp;

import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("articleHotAmqp")
public class ArticleHotAmqp implements ArticleAmqp {
    private final RabbitTemplate rabbitTemplate;
    private final JsonUtil jsonUtil;

    /**
     * 添加文章热度所使用的交换机
     */
    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE = "articleHotValueExchange";

    public ArticleHotAmqp(RabbitTemplate rabbitTemplate, JsonUtil jsonUtil) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonUtil = jsonUtil;
    }

    /**
     * 添加文章对应的热度信息
     * @param articleId 文章id
     */
    public void insertHotArticle(String articleId){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE,"article.hot.value.default",articleId);
    }

    /**
     * @param articleId 文章id
     */
    public void deleteHotArticle(String articleId){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE_HOT_VALUE,"article.hot.value.delete",articleId);
    }
}
