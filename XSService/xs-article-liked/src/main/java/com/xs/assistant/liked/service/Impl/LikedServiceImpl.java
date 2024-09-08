package com.xs.assistant.liked.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.liked.DAO.LikedDAO;
import com.xs.assistant.liked.service.abstracts.AbstractLikedService;
import com.xs.assistant.liked.service.*;
import com.xs.assistant.liked.service.remote.AccountRemoteService;
import com.xs.assistant.liked.service.remote.ArticleHotRemoteService;
import com.xs.assistant.redis.util.RedisUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LikedServiceImpl extends AbstractLikedService implements LikedService {

    final LikedDAO likedDAO;
    final AccountRemoteService accountService;
    final LikedScheduled likedScheduled;
    final ArticleHotRemoteService articleHotRemoteService;
    @Resource
    RedisUtil redisUtil;
    private volatile boolean hasLiked = false;

    public LikedServiceImpl(LikedDAO likedDAO, AccountRemoteService accountService, LikedScheduled likedScheduled, ArticleHotRemoteService articleHotRemoteService) {
        this.likedDAO = likedDAO;
        this.accountService = accountService;
        this.likedScheduled = likedScheduled;
        this.articleHotRemoteService = articleHotRemoteService;
    }

    /**
     * 点赞
     * @param articleId 文章id
     * @param accountId 用户id
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "liked-api",fallbackMethod = "fallLiked")
    public ResponseResult<Integer> likedByArticleId(String articleId, String accountId) {
        String key = LIKED_KEY + articleId;
        if(redisUtil.hasHashKey(key, accountId))
            return hasLikedByAccount();
        if(Boolean.FALSE.equals(accountService.checkCustomerByID(accountId).getData()))
            return userNotExist();
        articleHotRemoteService.addLiked(articleId);
        redisUtil.setHashForever(key,accountId,1);
        redisUtil.setOrUpdateZSet(REDIS_LIKED_KEY,articleId);
        return successLiked("点赞成功");
    }

    /**
     * 取消点赞
     * @param articleId 文章id
     * @param accountId 用户id
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "liked-api",fallbackMethod = "fallLiked")
    public ResponseResult<Integer> unLikedByArticleId(String articleId, String accountId) {
        String key = LIKED_KEY + articleId;
        if(!redisUtil.hasHashKey(key,accountId))
            return hasUnLikedByAccount();
        if(Boolean.FALSE.equals(articleHotRemoteService.cancelLiked(articleId)))
            return fallLiked(new RuntimeException());
        long rsLine = redisUtil.deleteHash(key,accountId);
        if(rsLine <= 0)
            throw new RuntimeException();
        redisUtil.setMinusScore(REDIS_LIKED_KEY,articleId);
        return successLiked("取消成功");
    }

    /**
     * 旧的存入方法
     * 已弃用
     * @param key key
     * @param accountId 用户id
     * @param articleId 用户id
     */
    @Deprecated
    private void oldLikedFunction(String key,String accountId,String articleId){
        redisUtil.setHashForever(key,accountId,1);
        redisUtil.setOrUpdateZSet(REDIS_LIKED_KEY,articleId);
        if(!hasLiked){
            hasLiked = true;
            ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
            scheduled.scheduleAtFixedRate(likedScheduled,60,60, TimeUnit.SECONDS);
        }
    }
}
