package com.xs.assistant.redis.Aspect;

import com.xs.assistant.redis.Aspect.Annotation.RedisSet;
import com.xs.assistant.redis.Util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
