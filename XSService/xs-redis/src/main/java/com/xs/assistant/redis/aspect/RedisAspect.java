package com.xs.assistant.redis.aspect;

import com.xs.assistant.redis.aspect.Annotation.RedisSet;
import com.xs.assistant.redis.aspect.Annotation.RedisSetHash;
import com.xs.assistant.redis.aspect.Annotation.RedisSetHashValues;
import com.xs.assistant.redis.aspect.Exception.RedisKeyNullException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public abstract class RedisAspect {
    private final SpelExpressionParser parser = new SpelExpressionParser();

    public String getSpELKey(ProceedingJoinPoint joinPoint,String keyExpression) throws RedisKeyNullException{
        if(keyExpression.isBlank())
            throw new RedisKeyNullException();
        if(!keyExpression.contains("#"))
            return keyExpression;
        Expression expression = parser.parseExpression(keyExpression);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        for(int i = 0;i < args.length;i++)
            context.setVariable(paramNames[i],args[i]);
        return expression.getValue(context,String.class);
    }

    public String getSpELKeyByRedisKey(ProceedingJoinPoint joinPoint,RedisSet redisSet){
        return getSpELKey(joinPoint,redisSet.key());
    }

    public RedisHashActuatorAspect.KeyEntry getSpELKeyByRedisHash(ProceedingJoinPoint joinPoint, RedisSetHash redisSetHash) {
        return new RedisHashActuatorAspect.KeyEntry(getSpELKey(joinPoint, redisSetHash.keyName())
                , getSpELKey(joinPoint, redisSetHash.key()));
    }
    public RedisHashActuatorAspect.KeyEntry getSpELKeyByRedisHash(ProceedingJoinPoint joinPoint, RedisSetHashValues redisSetHashValues) {
        return new RedisHashActuatorAspect.KeyEntry(getSpELKey(joinPoint, redisSetHashValues.keyName())
                , getSpELKey(joinPoint, redisSetHashValues.key()));
    }
}
