package com.xs.assistant.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class MessagePublisher {
    private final RedisTemplate<Object,Object> redisTemplate;

    public MessagePublisher(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean publish(String channel,Object message){
        if(!StringUtils.hasText(channel))
            return false;
        try {
            redisTemplate.convertAndSend(channel,message);
            log.info("发送消息成功, Channel {}, Message {}",channel,message);
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("发送消息失败, Channel: {}, Message: {}",channel,message);
            return false;
        }
        return true;
    }
}
