package com.xs.assistant.redis.lock;

import com.xs.assistant.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁
 */
@Slf4j
public class RedisDistributedLock implements Lock {
    private final RedisUtil redisUtil;

    private final String lockName;
    private final String uuidValue;
    private final long expireTime;

    private static final String LOCK_SCRIPT = """
            if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists', KEYS[1], ARGV[1]) == 1 then
                redis.call('hincrby', KEYS[1], ARGV[1], 1)
                redis.call('expire', KEYS[1], ARGV[2])
                return 1
            else
                return 0
            end
            """;

    private static final String UNLOCK_SCRIPT = """
            if redis.call('hexists', KEYS[1], ARGV[1]) == 0 then
                return nil
            else if redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 then
                return redis.call('del', KEYS[1])
            else
                return 0
            end
            """;

    private static final String REEXPIRE_SCRIPT = """
            if redis.call('hexists', KEYS[1], ARGV[1]) == 1 then
                return redis.call('expire', KEYS[1], ARGV[2])
            else
                return 0
            end
            """;

    public RedisDistributedLock(RedisUtil redisUtil, String lockName, long expireTime) {
        this.redisUtil = redisUtil;
        this.lockName = lockName;
        this.uuidValue = UUID.randomUUID() + ":" + Thread.currentThread().getId();
        this.expireTime = expireTime;
    }

    @Override
    public void lock() {
        tryLock();
    }

    /**
     * 锁中断
     */
    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return tryLock(-1L,TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        if(time == -1L){
            while (!redisUtil.scriptLua(LOCK_SCRIPT,Boolean.class, List.of(lockName),uuidValue,String.valueOf(expireTime))){
                try {
                    TimeUnit.MICROSECONDS.sleep(60);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            renewExpire();
            return true;
        }
        return false;
    }

    @Override
    public void unlock() {
        Long flag = redisUtil.scriptLua(UNLOCK_SCRIPT, Long.class, List.of(lockName), uuidValue);
        if(flag == null){
            log.error("This lock doesn't exist");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private void renewExpire(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (redisUtil.scriptLua(REEXPIRE_SCRIPT, Boolean.class, List.of(lockName), uuidValue, String.valueOf(expireTime))) {
                    renewExpire();
                }
            }
        },(this.expireTime * 1000) >> 1);
    }
}
