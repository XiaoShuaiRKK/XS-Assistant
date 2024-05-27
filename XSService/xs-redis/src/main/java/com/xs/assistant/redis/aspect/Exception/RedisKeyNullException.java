package com.xs.assistant.redis.aspect.Exception;

public class RedisKeyNullException extends RuntimeException{
    public RedisKeyNullException() {
        super("The Annotation RedisSet key is NULL");
    }
}
