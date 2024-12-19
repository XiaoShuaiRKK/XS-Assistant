package com.xs.assistant.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    final RedisTemplate<String,Object> redisTemplate;

    public static final long DEFAULT_EXPIRE_TIME = 60L;

    public RedisUtil(@Qualifier("myRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * The data set in redis
     * default expiration time of 60s
     * @param key key
     * @param value value
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

    public boolean setForever(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
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
    public boolean rightPushList(String key,List<?> value){
        return rightPushList(key,value,DEFAULT_EXPIRE_TIME);
    }

    public boolean rightPushList(String key,List<?> value,Long time){
        return rightPushList(key,value,time,TimeUnit.SECONDS);
    }

    public boolean rightPushList(String key, List<?> value,Long time,TimeUnit timeUnit){
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
            log.error("=========setHash() Error=========");
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean setHashForever(String key,String itemKey,Object value){
        try {
            redisTemplate.opsForHash().put(key,itemKey,value);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public <T> boolean setHashMap(String key,Map<String,T> value){
        return setHashMap(key,value,DEFAULT_EXPIRE_TIME);
    }

    public <T> boolean setHashMap(String key,Map<String,T> value,Long time){
        return setHashMap(key,value,time,TimeUnit.SECONDS);
    }

    public <T> boolean setHashMap(String key, Map<String,T> value,Long time,TimeUnit timeUnit){
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

    public void setIfPresent(String key,Object value){
        setIfPresent(key,value,DEFAULT_EXPIRE_TIME);
    }

    public void setIfPresent(String key,Object value,Long time){
        setIfPresent(key,value,time,TimeUnit.SECONDS);
    }

    public void setIfPresent(String key,Object value,Long time,TimeUnit timeUnit){
        try {
            redisTemplate.opsForValue().setIfPresent(key,value,time,timeUnit);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public double setOrUpdateZSet(String key,String value){
        return setOrUpdateZSet(key,value,1D);
    }

    public double setMinusScore(String key,String value){
        return setOrUpdateZSet(key,value,-1D);
    }

    public double setOrUpdateZSet(String key,String value,double score){
        Double d = redisTemplate.opsForZSet().incrementScore(key,value,score);
        return d == null ? 0D : d;
    }

    public Set<Object> getZSet(String key, long s, long e){
        return redisTemplate.opsForZSet().reverseRange(key,s,e);
    }

    public Object getZSetScoreByKey(String key,String value){
        return redisTemplate.opsForZSet().score(key,value);
    }

    public Set<ZSetOperations.TypedTuple<Object>> getZSetScoresByKey(String key,long s,long e){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key,s,e);
    }

    public Set<ZSetOperations.TypedTuple<Object>> getAllZSetScoresByKey(String key){
        return getZSetScoresByKey(key,0L,-1L);
    }

    public List<Double> getZSetScoreByKey(String key,Object... value){
        return redisTemplate.opsForZSet().score(key,value);
    }

    public Set<Object> getAllZSet(String key){
        return getZSet(key,0,-1);
    }

    public Long increment(String key){
        return increment(key,1L);
    }

    public Long increment(String key,long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Long incrementForHash(String key,String value){
        return redisTemplate.opsForHash().increment(key,value,1L);
    }

    public Long incrementForHash(String key,String value,long delta){
        return redisTemplate.opsForHash().increment(key,value,delta);
    }

    public Long incrementByExpire(String key,long time,TimeUnit timeUnit){
        return incrementByExpire(key,1L,time,timeUnit);
    }

    public Long incrementByExpire(String key,long delta,long time,TimeUnit timeUnit){
        long result = increment(key,delta);
        setExpire(key,time,timeUnit);
        return result;
    }

    public Long incrementForHashByExpire(String key,String itemKey,long time,TimeUnit timeUnit){
        long result = incrementForHash(key,itemKey);
        setExpire(key,time,timeUnit);
        return result;
    }

    public Long incrementForHashByExpire(String key,String itemKey,long delta,long time,TimeUnit timeUnit){
        long result = incrementForHash(key,itemKey,delta);
        setExpire(key,time,timeUnit);
        return result;
    }

    public <K,V> Map<K,V> getHashAll(String key){
        return (Map<K, V>) redisTemplate.opsForHash().entries(key);
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

    public boolean hasHashKey(String key,String itemKey){
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

    public void getHashByCursor(String key,int count){
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().count(count).build());

    }

    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public Long deleteHash(String key,String itemKey) {
        return redisTemplate.opsForHash().delete(key,itemKey);
    }

    public Long deleteHash(String key){
        return redisTemplate.opsForHash().delete(key);
    }

    public Long hyperLogLogAdd(String key,Object value){
        return redisTemplate.opsForHyperLogLog().add(key,value);
    }

    public Long hyperLogLogCount(String key){
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    public boolean setBit(String key,Integer index,boolean value){
        return redisTemplate.opsForValue().setBit(key,index,value);
    }

    public boolean containsBit(String key,Integer index){
        return redisTemplate.opsForValue().getBit(key,index);
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

//    public <T> T scriptLua(String scriptPath,Class<T> clz,String[] key,String args){
//        DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPath)));
//        redisScript.setResultType(clz);
//        return redisTemplate.execute(redisScript, Collections.singletonList(key),args);
//    }

    public <T> T scriptLua(String script,String key,String field,int incrementBy){
        return redisTemplate.execute((RedisCallback<? extends T>) connection -> (T)connection.eval(
                script.getBytes(),
                ReturnType.INTEGER,
                incrementBy,
                key.getBytes(),
                field.getBytes(),
                String.valueOf(incrementBy).getBytes()
        ));
    }

//    public List<Object> pipeline(RedisCallback<?> callback){
//        return redisTemplate.executePipelined(callback);
//    }

    public List<Object> pipeline(SessionCallback<?> callback){
        return redisTemplate.executePipelined(callback);
    }

    public Map.Entry<Object,Object> fuzzySearchHashKeys(String hashKey,String pattern){
        Map.Entry<Object,Object> result = null;
        try(Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(hashKey, ScanOptions.scanOptions().match(pattern).build())){
            while (scan.hasNext()){
                result = scan.next();
            }
        }catch (Exception e){
            log.error("=========fuzzySearchHashKeys Error=================");
            log.error(e.getMessage());
        }
        return result;
    }


    public static class SessionCallbackFactory{
        public enum OperationsEnum{
            SET,SET_FOREVER,SET_IF_PRESENT,SET_IF_PRESENT_FOREVER,DELETE
        }

        @FunctionalInterface
        public interface SessionOperationsNoReturn{
            void execute(RedisOperations<Object,Object> operations,String key,Object value,long time,TimeUnit timeUnit);
        }

        private static Map<OperationsEnum,SessionOperationsNoReturn> operationsMap = new EnumMap<>(OperationsEnum.class);

        static {
            operationsMap.put(OperationsEnum.SET, (operations, key, value, time, timeUnit) -> operations.opsForValue().set(key,value,time,timeUnit));
            operationsMap.put(OperationsEnum.SET_FOREVER, (operations, key, value, time, timeUnit) -> operations.opsForValue().set(key,value));
            operationsMap.put(OperationsEnum.SET_IF_PRESENT, (operations, key, value, time, timeUnit) -> operations.opsForValue().setIfPresent(key,value,time,timeUnit));
            operationsMap.put(OperationsEnum.SET_IF_PRESENT_FOREVER, (operations, key, value, time, timeUnit) -> operations.opsForValue().setIfPresent(key,value));
            operationsMap.put(OperationsEnum.DELETE, (operations, key, value, time, timeUnit) -> operations.delete(key));
        }


        public static SessionCallback<Object> batchOperationsNoReturn(Map<String,Object> itemMap,long time,TimeUnit timeUnit,OperationsEnum operationsEnum){
            return new SessionCallback<>() {
                @Override
                public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                    SessionOperationsNoReturn sessionOperationsNoReturn = operationsMap.get(operationsEnum);
                    itemMap.forEach((k,v) -> sessionOperationsNoReturn.execute((RedisOperations<Object, Object>) operations,k,v,time,timeUnit));
                    return null;
                }
            };
        }
    }
}
