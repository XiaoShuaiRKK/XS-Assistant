package com.xs.assistant.redis.filter;

import com.xs.assistant.redis.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;

public class FilterFactory {
    public enum FilterType {
        BLOOM, CUCKOO
    }

    static Map<String,RedisFilter> filterMap = new HashMap<>();

    public static RedisFilter createFilter(FilterType filterType, RedisUtil redisUtil,
                                           String name, int numberOfHashes, int bitArraySize) {
        if(filterMap.containsKey(name)) {
            return filterMap.get(name);
        }
        RedisFilter filter = switch (filterType) {
            case BLOOM -> new BloomFilter(redisUtil, name, numberOfHashes, bitArraySize);
            case CUCKOO -> new CuckooFilter();
        };
        filterMap.put(name, filter);
        return filter;
    }

    public static RedisFilter createDefaultFilter(FilterType filterType, RedisUtil redisUtil,String name) {
        return createFilter(filterType, redisUtil, name, 3, 1024);
    }
}
