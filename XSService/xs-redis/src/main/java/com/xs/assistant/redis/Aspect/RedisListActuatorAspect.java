package com.xs.assistant.redis.Aspect;

import com.xs.assistant.redis.Aspect.Annotation.RedisSetList;
import com.xs.assistant.redis.Util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RedisListActuatorAspect extends RedisAspect{
    @Autowired
    RedisUtil redisUtil;

    @Around("@annotation(redisSetList)")
    public Object cacheableRedisList(ProceedingJoinPoint joinPoint, RedisSetList redisSetList) throws Throwable {
        String key = getSpELKey(joinPoint,redisSetList.key());
        if(redisUtil.hasKey(key))
            return redisUtil.getList(key);
        List<?> result = (List<?>) joinPoint.proceed();
        redisUtil.setList(key,result,redisSetList.time(),redisSetList.timeStyle());
        return result;
    }

}
