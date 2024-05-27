package com.xs.assistant.redis.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configurable
@EnableCaching
public class RedisConfig {
    @Bean
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


    private RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer(){
        return new GenericToStringSerializer<>(Object.class);
    }
}
