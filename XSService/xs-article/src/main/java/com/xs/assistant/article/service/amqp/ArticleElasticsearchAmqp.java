package com.xs.assistant.article.service.amqp;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleESAmqp")
public class ArticleElasticsearchAmqp implements ArticleAmqp {

    private final RabbitTemplate rabbitTemplate;
    private final JsonUtil jsonUtil;

    /**
     * 添加文章所使用的交换机
     */
    private static final String RABBITMQ_EXCHANGE_NAME_ARTICLE = "articleChange";

    public ArticleElasticsearchAmqp(RabbitTemplate rabbitTemplate, JsonUtil jsonUtil) {
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

    public void batchUploadArticle(List<ArticleContext> articles){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE,"article.batch.add",jsonUtil.beanToJson(articles));
    }

    public void deleteArticle(String articleId){
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_NAME_ARTICLE,"article.single.delete",articleId);
    }
}
