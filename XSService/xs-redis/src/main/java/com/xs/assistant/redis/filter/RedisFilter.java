package com.xs.assistant.redis.filter;

public interface RedisFilter {
    void add(String value);
    boolean contains(String value);
}
