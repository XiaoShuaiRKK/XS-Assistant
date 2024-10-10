package com.xs.assistant.hot.service.listener;

import com.xs.assistant.hot.service.ArticleHotInsertService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleHotInsertListener {
    final ArticleHotInsertService articleHotInsertService;

    public ArticleHotInsertListener(ArticleHotInsertService articleHotInsertService) {
        this.articleHotInsertService = articleHotInsertService;
    }

    /**
     * 插入文章热度
     * @param articleId 文章id
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(value = "articleHotValueExchange",type = "topic"),
            key = "article.hot.value.default"))
    private void insertDefaultHot(String articleId){
        articleHotInsertService.insertDefaultHot(articleId);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(value = "articleHotValueExchange",type = "topic"),
            key = "article.hot.value.delete"))
    private void deleteHot(String articleId){
        articleHotInsertService.deleteHot(articleId);
    }
}
