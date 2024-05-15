package com.xs.assistant.hot.service.listener;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.DAO.ArticleHotMapper;
import com.xs.assistant.hot.service.util.HotValueUtil;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.ElasticsearchUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleHotListener {
    final ElasticsearchUtil elasticsearchUtil;
    final ArticleHotMapper articleHotMapper;
    final HotValueUtil hotValueUtil;
    final RedisUtil redisUtil;

    public ArticleHotListener(ElasticsearchUtil elasticsearchUtil, ArticleHotMapper articleHotMapper, HotValueUtil hotValueUtil, RedisUtil redisUtil) {
        this.elasticsearchUtil = elasticsearchUtil;
        this.articleHotMapper = articleHotMapper;
        this.hotValueUtil = hotValueUtil;
        this.redisUtil = redisUtil;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = ArticleHotKey.RABBITMQ_EXCHANGE_NAME,type = "topic"),
            key = ArticleHotKey.RABBITMQ_EXCHANGE_KEY_COLUMN))
    private void hotListening(String articleId){
        ArticleHot articleHot = articleHotMapper.selectByArticleId(articleId);
        double hotValue = hotValueUtil.calculateHotValue(articleHot,1);
        elasticsearchUtil.insertDocument(ArticleHotKey.ES_ARTICLE_INDEX_NAME,articleId,
                ArticleContext.builder().id(articleId).hot(hotValue).build());
        redisUtil.setOrUpdateZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY,articleId,
                hotValue);
    }
}
