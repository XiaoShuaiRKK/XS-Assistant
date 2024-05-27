package com.xs.assistant.hot.service.Impl;

import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.service.SearchArticleHot;
import com.xs.assistant.redis.util.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ArticleHotSearchServiceImpl implements SearchArticleHot {
    final RedisUtil redisUtil;

    public ArticleHotSearchServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 获取热度排行
     * @return 热度排行
     */
    @Override
    public Set<Object> getHotTop() {
        return redisUtil.getAllZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY);
    }

    /**
     * 获取热度排行
     * @param s 起始排行
     * @param e 结束排行
     * @return 热度排行
     */
    @Override
    public Set<Object> getHotTop(long s, long e) {
        return redisUtil.getZSet(ArticleHotKey.REDIS_ARTICLE_HOT_KEY,s,e);
    }

    /**
     * 获取热度排行
     * @param target 关键字
     * @return 热度排行
     */
    @Override
    public Set<Object> getHotTop(String target) {
        return Set.of();
    }
}
