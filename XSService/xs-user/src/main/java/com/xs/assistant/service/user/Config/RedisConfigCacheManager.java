package com.xs.assistant.service.user.Config;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * 设置redis 注解 过期时间
 */
public class RedisConfigCacheManager extends RedisCacheManager {
    public RedisConfigCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if(!StringUtils.isEmpty(name) && name.contains("#")){
            String[] SPEL = name.split("#");
            long cycle = Long.parseLong(SPEL[1]);
            switch (SPEL[2]){
                case "s" -> super.createRedisCache(SPEL[0],cacheConfig.entryTtl(Duration.ofSeconds(cycle)));
                case "m" -> super.createRedisCache(SPEL[0],cacheConfig.entryTtl(Duration.ofMinutes(cycle)));
                case "h" -> super.createRedisCache(SPEL[0],cacheConfig.entryTtl(Duration.ofHours(cycle)));
                case "d" -> super.createRedisCache(SPEL[0],cacheConfig.entryTtl(Duration.ofDays(cycle)));
            }
        }
        return super.createRedisCache(name,cacheConfig);
    }
}
