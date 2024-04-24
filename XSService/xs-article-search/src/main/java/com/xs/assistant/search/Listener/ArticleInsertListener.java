package com.xs.assistant.search.Listener;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.search.Service.ESInsertService;
import com.xs.assistant.search.Service.ESSearchService;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleInsertListener {
    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    ESInsertService esInsertService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "articleChange",type = "topic"),
            key = "article.single"))
    private void addSingleArticle(String articleJson){
        esInsertService.insert(jsonUtil.jsonToBean(articleJson, ArticleMongoDO.class));
    }
}
