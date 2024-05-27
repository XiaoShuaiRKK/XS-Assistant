package com.xs.assistant.util.function;

@FunctionalInterface
public interface RedisKeyFunction<K,U> {
    U execute(K key,U u);

    default RedisKeyFunction<K,U> hasKey(boolean has,RedisKeyFunction<K,U> redisKeyFunction){
        return has ? this : redisKeyFunction;
    }
}
