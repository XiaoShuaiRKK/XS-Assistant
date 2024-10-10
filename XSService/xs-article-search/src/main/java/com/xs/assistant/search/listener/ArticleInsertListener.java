package com.xs.assistant.search.listener;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.search.service.ESInsertService;
import com.xs.assistant.search.service.ESUpdateService;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ArticleInsertListener {
    final JsonUtil jsonUtil;
    final ESInsertService esInsertService;
    final ESUpdateService esUpdateService;

    public ArticleInsertListener(JsonUtil jsonUtil, ESInsertService esInsertService, ESUpdateService esUpdateService) {
        this.jsonUtil = jsonUtil;
        this.esInsertService = esInsertService;
        this.esUpdateService = esUpdateService;
    }

    /**
     * 监听文章插入
     * @param articleJson json
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "articleChange",type = "topic"),
            key = "article.single"))
    private void addSingleArticle(String articleJson){
        esInsertService.insert(jsonUtil.jsonToBean(articleJson, ArticleContext.class));
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "articleChange",type = "topic"),
            key = "article.single.delete"))
    private void deleteSingleArticle(String articleId){
        esUpdateService.deleteArticle(articleId);
    }
}
