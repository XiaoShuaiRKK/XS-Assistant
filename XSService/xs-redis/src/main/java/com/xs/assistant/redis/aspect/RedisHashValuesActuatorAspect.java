package com.xs.assistant.redis.aspect;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.aspect.Annotation.RedisSetHashValues;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisHashValuesActuatorAspect extends RedisHashActuatorAspect {

    @Around("@annotation(redisSetHashValues)")
    public Object cacheableRedisSet(ProceedingJoinPoint joinPoint, RedisSetHashValues redisSetHashValues) throws Throwable {
        KeyEntry keyEntry = getSpELKeyByRedisHash(joinPoint,redisSetHashValues);
        String realKey;
        boolean isIDNumber = checkIDNumber(keyEntry.getKey());
        if(redisUtil.hasKey(keyEntry.getKeyName(),keyEntry.getKey())){
            if(isIDNumber)
                return redisUtil.getHash(redisSetHashValues.baseKey(),keyEntry.getKey());
            realKey = (String) redisUtil.getHash(keyEntry.getKeyName(),keyEntry.getKey());
            return redisUtil.getHash(redisSetHashValues.baseKey(),realKey);
        }
        CustomerDO result = (CustomerDO) joinPoint.proceed();
        if(result != null){
            redisUtil.setHash(redisSetHashValues.baseKey(),result.getIdNumber()
                    ,result,redisSetHashValues.time(),redisSetHashValues.timeStyle());
            if(!isIDNumber)
                redisUtil.setHash(keyEntry.getKeyName(),redisSetHashValues.key()
                    ,result.getIdNumber(),redisSetHashValues.time(),redisSetHashValues.timeStyle());
        }
        return result;
    }

    private boolean checkIDNumber(String key){
        return key.startsWith("XS");
    }
}
