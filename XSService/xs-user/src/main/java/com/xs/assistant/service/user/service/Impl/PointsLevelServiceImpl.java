package com.xs.assistant.service.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.service.user.DAO.PointsLevelMapper;
import com.xs.assistant.service.user.service.PointsLevelService;
import com.xs.assistant.util.Impl.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PointsLevelServiceImpl implements PointsLevelService {

    private static final String POINTS_INC_KEY = "points:inc";
    private static final String POINTS_TOPIC_KEY = "points-topic";

    final JsonUtil jsonUtil;
    final KafkaTemplate<String,String> kafkaTemplate;
    final RedisUtil redisUtil;
    final PointsLevelMapper pointsLevelMapper;

    public PointsLevelServiceImpl(JsonUtil jsonUtil, KafkaTemplate<String, String> kafkaTemplate, RedisUtil redisUtil, PointsLevelMapper pointsLevelMapper) {
        this.jsonUtil = jsonUtil;
        this.kafkaTemplate = kafkaTemplate;
        this.redisUtil = redisUtil;
        this.pointsLevelMapper = pointsLevelMapper;
    }

    @Override
    public String createPointsLevel(String customerId) {
        String pointsId = new StringBuilder(customerId).insert(2,"P").toString();
        PointsLevel pointsLevel = new PointsLevel(pointsId, 1,"", 0,0,0);
        int insert = pointsLevelMapper.insert(pointsLevel);
        return insert > 0 ? customerId : null;
    }

    @Override
    public PointsLevel getPointsLevel(String pointsLevelId) {
        return pointsLevelMapper.selectPointsLevelById(pointsLevelId);
    }

    public Boolean updatePointsLevel(String pointsLevelId, Integer points) {
        LambdaUpdateWrapper<PointsLevel> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(PointsLevel::getPoints,points)
                .eq(PointsLevel::getPointsId,pointsLevelId);
        return pointsLevelMapper.update(updateWrapper) > 0;
    }

    @Override
    public Boolean increasePointsLevel(String pointsLevelId, Integer points) {
        return redisUtil.incrementForHashByExpire(POINTS_INC_KEY,pointsLevelId,points,60, TimeUnit.MINUTES) > 0;
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void updatePoints(){
        log.info("Points Level Service Impl: Update Points Level");
        Map<Object,Object> experienceData = redisUtil.getHashEntries(POINTS_INC_KEY);
        if(!experienceData.isEmpty()){
            kafkaTemplate.send(POINTS_TOPIC_KEY,jsonUtil.beanToJson(experienceData));
            redisUtil.delete(POINTS_INC_KEY);
        }
    }
}
