package com.xs.assistant.redis.listener;

import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageChannelUtil {
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final MessageListenerAdapter listenerAdapter;

    public MessageChannelUtil(RedisMessageListenerContainer redisMessageListenerContainer,
                              MessageListenerAdapter listenerAdapter) {
        this.redisMessageListenerContainer = redisMessageListenerContainer;
        this.listenerAdapter = listenerAdapter;
    }

    public boolean addChannel(String channelName){
        if(!StringUtils.hasText(channelName))
            return false;
        redisMessageListenerContainer.addMessageListener(listenerAdapter,new PatternTopic(channelName));
        return true;
    }

}
