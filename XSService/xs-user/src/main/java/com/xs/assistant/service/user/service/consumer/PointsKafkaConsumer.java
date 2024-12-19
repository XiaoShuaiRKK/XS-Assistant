package com.xs.assistant.service.user.service.consumer;

import com.google.common.reflect.TypeToken;
import com.xs.assistant.service.user.DAO.PointsLevelMapper;
import com.xs.assistant.util.Impl.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PointsKafkaConsumer {
    private static final String POINTS_TOPIC_KEY = "points-topic";
    private static final String POINTS_GROUP_ID = "points-increment-group";

    final JsonUtil jsonUtil;
    final PointsLevelMapper pointsLevelMapper;

    public PointsKafkaConsumer(JsonUtil jsonUtil, PointsLevelMapper pointsLevelMapper) {
        this.jsonUtil = jsonUtil;
        this.pointsLevelMapper = pointsLevelMapper;
    }

    @KafkaListener(topics = POINTS_TOPIC_KEY,groupId =POINTS_GROUP_ID)
    public void handlePointsMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        Map<String,Integer> userPointsData = jsonUtil.jsonToBean(message, new TypeToken<Map<String,Integer>>(){}.getType());
        pointsLevelMapper.batchUpdatePoints(userPointsData);
    }
}
