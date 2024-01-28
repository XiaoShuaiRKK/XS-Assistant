package com.xs.assistant.redis.Util;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public static final long DEFAULT_EXPIRE_TIME = 60L;

    /**
     * The data set in redis
     * default expiration time of 60s
     * @param key key
     * @param value vaule
     * @return boolean
     */
    public boolean set(String key,Object value){
        return set(key,value,DEFAULT_EXPIRE_TIME);
    }

    public boolean set(String key,Object value,Long time){
        return set(key,value,time,TimeUnit.SECONDS);
    }

    public boolean set(String key, Object value, Long time, TimeUnit timeUnit){
        try {
            redisTemplate.opsForValue().set(key,value,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * The data set in redis in form of a List
     * @param key key
     * @param value value
     * @return boolean
     */
    public boolean setList(String key,List<?> value){
        return setList(key,value,DEFAULT_EXPIRE_TIME);
    }

    public boolean setList(String key,List<?> value,Long time){
        return setList(key,value,time,TimeUnit.SECONDS);
    }

    public boolean setList(String key, List<?> value,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            setExpire(key,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * The data set in redis in form of a hash
     * @param key key
     * @param itemKey itemKey
     * @param value value
     * @return boolean
     */
    public boolean setHash(String key,String itemKey,Object value){
        return setHash(key,itemKey,value,DEFAULT_EXPIRE_TIME);
    }

    public boolean setHash(String key,String itemKey,Object value,Long time){
        return setHash(key,itemKey,value,time,TimeUnit.SECONDS);
    }

    public boolean setHash(String key,String itemKey,Object value,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.opsForHash().put(key,itemKey,value);
            setExpire(key,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean setHashMap(String key,Map<String,Object> value){
        return setHashMap(key,value,DEFAULT_EXPIRE_TIME);
    }

    public boolean setHashMap(String key,Map<String,Object> value,Long time){
        return setHashMap(key,value,time,TimeUnit.SECONDS);
    }

    public boolean setHashMap(String key, Map<String,Object> value,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.opsForHash().putAll(key,value);
            setExpire(key,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public void setIfAbsent(String key,Object value){
        setIfAbsent(key,value,DEFAULT_EXPIRE_TIME);
    }

    public void setIfAbsent(String key,Object value,Long time){
        setIfAbsent(key,value,time,TimeUnit.SECONDS);
    }

    public void setIfAbsent(String key,Object value,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.opsForValue().setIfAbsent(key,value,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Long increment(String key){
        return increment(key,1L);
    }

    public Long increment(String key,long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public List<?> getList(String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }

    public Object getListByIndex(String key,Integer index){
        return redisTemplate.opsForList().index(key,index);
    }

    public Long getListSize(String key){
        return redisTemplate.opsForList().size(key);
    }

    public Object getHash(String key,String itemKey){
        return redisTemplate.opsForHash().get(key,itemKey);
    }

    public List<?> multiGetHash(String key,String... itemKey){
        return redisTemplate.opsForHash().multiGet(key,List.of(itemKey));
    }

    public boolean hasKey(String key,String itemKey){
        return redisTemplate.opsForHash().hasKey(key,itemKey);
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Set<?> getHashKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    public List<?> getHashValues(String key){
        return redisTemplate.opsForHash().values(key);
    }

    public Map<Object,Object> getHashEntries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean deleteHash(String key,String itemKey){
        try {
            redisTemplate.opsForHash().delete(key,itemKey);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * set the expiration time for key
     * @param key key
     * @return boolean
     */
    public boolean setExpire(String key){
        return setExpire(key,DEFAULT_EXPIRE_TIME);
    }

    public boolean setExpire(String key,Long time){
        return setExpire(key,time,TimeUnit.SECONDS);
    }

    public boolean setExpire(String key,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.expire(key,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }


}
