package com.xs.assistant.liked.Service.Impl;

import com.xs.DAO.DO.liked.LikedDO;
import com.xs.assistant.liked.DAO.LikedDAO;
import com.xs.assistant.liked.Service.Abstract.AbstractLikedService;
import com.xs.assistant.redis.Util.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class LikedScheduled extends AbstractLikedService implements Runnable{
    @Autowired
    LikedDAO likedDAO;
    @Resource
    RedisUtil redisUtil;
    @Override
    @SuppressWarnings("all")
    public void run() {
        try {
            Set<ZSetOperations.TypedTuple<Object>> likedSet = redisUtil.getAllZSetScoresByKey(REDIS_LIKED_KEY);
            List<LikedDO> likeds = new ArrayList<>();
            likedSet.forEach(item -> {
                likeds.add(new LikedDO((String) item.getValue(),Math.round(item.getScore())));
            });
            int s = likedDAO.batchSaveOrUpdate(likeds);
            String msg = s > 0 ? "Update data success" : "Update data failed";
            log.info(msg);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
