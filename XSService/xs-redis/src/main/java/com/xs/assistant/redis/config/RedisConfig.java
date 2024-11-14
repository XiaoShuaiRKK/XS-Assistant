package com.xs.assistant.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xs.assistant.redis.listener.MessageSubscriber;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean("myRedisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        template.setHashKeySerializer(keySerializer());
        template.setHashValueSerializer(valueSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageSubscriber subscriber){
        return new MessageListenerAdapter(subscriber);
    }

    private RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer(){
        return new RedisSerializer<>() {

            @Override
            public byte[] serialize(Object o) throws SerializationException {
                if (o instanceof String) {
                    return new StringRedisSerializer().serialize((String) o);
                } else {
                    return new Jackson2JsonRedisSerializer<>(Object.class).serialize(o);
                }
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                String str = new String(bytes);
                if (str.contains(".")) {
                    return str;
                } else {
                    Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
                    return serializer.deserialize(bytes);
                }
            }
        };
    }
}
