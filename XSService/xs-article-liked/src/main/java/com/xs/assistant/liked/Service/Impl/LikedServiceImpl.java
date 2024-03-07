package com.xs.assistant.liked.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.liked.DAO.LikedDAO;
import com.xs.assistant.liked.Service.Abstract.AbstractLikedService;
import com.xs.assistant.liked.Service.*;
import com.xs.assistant.liked.Service.Remote.AccountRemoteService;
import com.xs.assistant.redis.Util.RedisUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LikedServiceImpl extends AbstractLikedService implements LikedService {

    @Autowired
    LikedDAO likedDAO;
    @Autowired
    AccountRemoteService accountService;
    @Autowired
    LikedScheduled likedScheduled;
    @Resource
    RedisUtil redisUtil;
    private volatile boolean hasLiked = false;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "liked-api",fallbackMethod = "fallLiked")
    public ResponseResult<Integer> likedByArticleId(String articleId, String accountId) {
        String key;
        if(redisUtil.hasKey(key = (LIKED_KEY + articleId), accountId))
            return hasLikedByAccount();
        if(!accountService.checkCustomerByID(accountId).getData())
            return userNotExist();
        redisUtil.setHashForever(key,accountId,1);
        redisUtil.setOrUpdateZSet(REDIS_LIKED_KEY,articleId);
        if(!hasLiked){
            hasLiked = true;
            ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
            scheduled.scheduleAtFixedRate(likedScheduled,60,60, TimeUnit.SECONDS);
        }
        return successLiked("点赞成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "liked-api",fallbackMethod = "fallLiked")
    public ResponseResult<Integer> unLikedByArticleId(String articleId, String accountId) {
        String key;
        if(!redisUtil.hasKey(key = (LIKED_KEY + articleId),accountId))
            return hasUnLikedByAccount();
        long rsLine = redisUtil.deleteHash(key,accountId);
        if(rsLine <= 0)
            throw new RuntimeException();
        redisUtil.setMinusScore(REDIS_LIKED_KEY,articleId);
        return successLiked("取消成功");
    }
}
