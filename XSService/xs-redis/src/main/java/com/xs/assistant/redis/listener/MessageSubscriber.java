package com.xs.assistant.redis.listener;

import com.xs.DAO.DTO.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class MessageSubscriber implements MessageListener {
    private final RedisTemplate<Object,Object> redisTemplate;

    public MessageSubscriber(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("channel: {}", new String(pattern));
        MessageDTO messageDTO = (MessageDTO) redisTemplate.getValueSerializer().deserialize(message.getBody());
        if (messageDTO != null) {
            log.info("{} , {}", messageDTO.getData(), messageDTO.getContent());
        }
    }
}
