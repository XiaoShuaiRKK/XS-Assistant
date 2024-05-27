package com.xs.assistant.redis.aspect;

import com.xs.assistant.redis.aspect.Annotation.RedisSetHash;
import com.xs.assistant.redis.util.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisHashActuatorAspect extends RedisAspect {
    @Autowired
    RedisUtil redisUtil;

    @Around("@annotation(redisSetHash)")
    public Object cacheableRedisSet(ProceedingJoinPoint joinPoint, RedisSetHash redisSetHash) throws Throwable {
        KeyEntry keyEntry = getSpELKeyByRedisHash(joinPoint,redisSetHash);
        if(redisUtil.hasKey(keyEntry.getKeyName(),keyEntry.getKey()))
            return redisUtil.getHash(keyEntry.getKeyName(),keyEntry.getKey());
        Object result = joinPoint.proceed();
        if(result != null)
            redisUtil.setHash(keyEntry.getKeyName(),keyEntry.getKey(),result,redisSetHash.time(),redisSetHash.timeStyle());
        return result;
    }

    @Getter
    @AllArgsConstructor
    public static class KeyEntry{
        private final String keyName;
        private final String key;
    }
}
