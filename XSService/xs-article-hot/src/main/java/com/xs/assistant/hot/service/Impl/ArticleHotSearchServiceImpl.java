package com.xs.assistant.hot.service.Impl;

import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.service.SearchArticleHot;
import com.xs.assistant.redis.Util.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleHotSearchServiceImpl implements SearchArticleHot {
    final RedisUtil redisUtil;

    public ArticleHotSearchServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public Set<Object> getHotTop() {
        return redisUtil.getAllZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY);
    }

    @Override
    public Set<Object> getHotTop(long s, long e) {
        return redisUtil.getZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY,s,e);
    }

    @Override
    public Set<Object> getHotTop(String target) {
        return Set.of();
    }
}
