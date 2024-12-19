package com.xs.assistant.redis.filter;

public class CuckooFilter implements RedisFilter {
    @Override
    public void add(String value) {

    }

    @Override
    public boolean contains(String value) {
        return false;
    }
}
