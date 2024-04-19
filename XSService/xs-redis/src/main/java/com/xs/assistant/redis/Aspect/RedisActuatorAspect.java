package com.xs.assistant.redis.Aspect;

import com.xs.assistant.redis.Aspect.Annotation.RedisSet;
import com.xs.assistant.redis.Util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisActuatorAspect extends RedisAspect {
    @Autowired
    RedisUtil redisUtil;

    @Around("@annotation(redisSet)")
    public Object cacheableRedisSet(ProceedingJoinPoint joinPoint,RedisSet redisSet) throws Throwable {
        String key = getSpELKeyByRedisKey(joinPoint,redisSet);
        if(redisUtil.hasKey(key))
            return redisUtil.get(key);
        Object result = joinPoint.proceed();
        if(result != null)
            redisUtil.set(key,result,redisSet.time(),redisSet.timeStyle());
        return result;
    }
}
