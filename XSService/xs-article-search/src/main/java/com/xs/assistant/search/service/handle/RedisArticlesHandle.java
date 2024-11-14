package com.xs.assistant.search.service.handle;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.option.article.ArticlesRedisKeyEnum;
import com.xs.assistant.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RedisArticlesHandle {
    final RedisUtil redisUtil;

    public RedisArticlesHandle(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

//    public void setArticlePage(int page, int size, List<ArticleContext> articles) {
//        Map<String,ArticleContext> articleMap = new HashMap<>();
//        articles.forEach(article -> articleMap.put(article.getId(),article));
//
//        redisUtil.setHashMap(ArticlesRedisKeyEnum.ARTICLES_KEY.getKey());
//    }
//
//    public List<ArticleContext> getArticlePage(int page, int size) {
//
//    }
}
