package com.xs.assistant.liked.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.liked.LikedDO;
import com.xs.assistant.liked.service.abstracts.AbstractLikedService;
import com.xs.assistant.liked.service.LikedTopService;
import com.xs.assistant.redis.util.RedisUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikedTopServiceImpl extends AbstractLikedService implements LikedTopService {
    final RedisUtil redisUtil;

    public LikedTopServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public ResponseResult<List<LikedDO>> getTop() {
        return null;
    }

    /**
     * 获取点赞排行榜
     * @param count
     * @return
     */
    @Override
    @SuppressWarnings("all")
    @CircuitBreaker(name = "liked-api",fallbackMethod = "fallLiked")
    public ResponseResult<List<LikedDO>> getTop(Long count) {
        List<LikedDO> result = new ArrayList<>();
        redisUtil.getZSetScoresByKey(REDIS_LIKED_KEY,0L,count).forEach(value ->
                result.add(new LikedDO((String) value.getValue(),Math.round(value.getScore()))));
        return ResponseResult.success(result);
    }
}
