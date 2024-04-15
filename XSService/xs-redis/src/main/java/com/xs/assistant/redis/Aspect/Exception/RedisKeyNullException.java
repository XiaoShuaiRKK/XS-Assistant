package com.xs.assistant.redis.Aspect.Exception;

public class RedisKeyNullException extends RuntimeException{
    public RedisKeyNullException() {
        super("The Annotation RedisSet key is NULL");
    }
}
