package com.xs.assistant.hot.service.listener;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.DAO.ArticleHotMapper;
import com.xs.assistant.hot.service.util.HotValueUtil;
import com.xs.assistant.redis.util.RedisUtil;
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

    /**
     * 热度插入
     * 将热度值计算并插入到elasticsearch中
     * @param articleId 文章id
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = ArticleHotKey.RABBITMQ_EXCHANGE_NAME,type = "topic"),
            key = ArticleHotKey.RABBITMQ_EXCHANGE_KEY_COLUMN))
    private void hotListening(String articleId){
        //查询
        ArticleHot articleHot = articleHotMapper.selectByArticleId(articleId);
        //计算热度值
        double hotValue = hotValueUtil.calculateHotValue(articleHot,1);
        elasticsearchUtil.insertDocument(ArticleHotKey.ES_ARTICLE_INDEX_NAME,articleId,
                ArticleContext.builder().id(articleId).hot(hotValue).build());
        redisUtil.setOrUpdateZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY,articleId,
                hotValue);
    }
}
