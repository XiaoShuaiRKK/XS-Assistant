package com.xs.assistant.redis.filter;

import com.xs.assistant.redis.util.RedisUtil;

public class BloomFilter implements RedisFilter {
    final RedisUtil redisUtil;
    final String key;
    final int numberOfHashes;
    final int bitArraySize;

    public BloomFilter(RedisUtil redisUtil, String key, int numberOfHashes, int bitArraySize) {
        this.redisUtil = redisUtil;
        this.key = key;
        this.numberOfHashes = numberOfHashes;
        this.bitArraySize = bitArraySize;
    }

    private int[] getHashPositions(String value){
        int[] positions = new int[numberOfHashes];
        for(int i = 0; i < numberOfHashes; i++){
            positions[i] = Math.abs((value.hashCode() + i) % bitArraySize);
        }
        return positions;
    }

    @Override
    public void add(String value) {
        int[] positions = getHashPositions(value);
        for(int position: positions){
            redisUtil.setBit(key, position, true);
        }
    }

    @Override
    public boolean contains(String value) {
        int[] positions = getHashPositions(value);
        for(int position: positions){
            if(!redisUtil.containsBit(key, position)){
                return false;
            }
        }
        return true;
    }
}
