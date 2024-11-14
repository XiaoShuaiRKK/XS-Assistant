package com.xs.assistant.hot.service.listener;

import com.google.gson.reflect.TypeToken;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.assistant.hot.service.ArticleHotInsertService;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleHotInsertListener {
    final JsonUtil jsonUtil;
    final ArticleHotInsertService articleHotInsertService;

    public ArticleHotInsertListener(JsonUtil jsonUtil, ArticleHotInsertService articleHotInsertService) {
        this.jsonUtil = jsonUtil;
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
            key = "article.hot.batch.values"))
    private void batchInsertDefaultHot(String articleIds){
        List<String> ids = jsonUtil.jsonToBean(articleIds, new TypeToken<List<String>>(){}.getType());
        articleHotInsertService.batchInsertDefaultHot(ids);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(value = "articleHotValueExchange",type = "topic"),
            key = "article.hot.value.delete"))
    private void deleteHot(String articleId){
        articleHotInsertService.deleteHot(articleId);
    }
}
