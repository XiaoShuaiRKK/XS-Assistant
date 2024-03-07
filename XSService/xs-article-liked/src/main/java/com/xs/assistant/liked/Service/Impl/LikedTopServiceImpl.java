package com.xs.assistant.liked.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.article.VO.ArticleVO;
import com.xs.DAO.liked.DO.LikedDO;
import com.xs.assistant.liked.Service.Abstract.AbstractLikedService;
import com.xs.assistant.liked.Service.LikedTopService;
import com.xs.assistant.redis.Util.RedisUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikedTopServiceImpl extends AbstractLikedService implements LikedTopService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public ResponseResult<List<LikedDO>> getTop() {
        return null;
    }

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
