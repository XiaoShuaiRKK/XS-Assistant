package com.xs.assistant.service.user.service.Impl;

import com.xs.assistant.redis.filter.FilterFactory;
import com.xs.assistant.redis.filter.RedisFilter;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.service.user.service.UserClockInService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserClockInServiceImpl implements UserClockInService {
    final RedisUtil redisUtil;

    public UserClockInServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public Boolean clockInUser(String idNumber){
        String filterName = "customer:clock-in:" + idNumber + ":bloom-filter";
        RedisFilter filter = FilterFactory.createFilter(FilterFactory.FilterType.BLOOM,redisUtil,filterName,1,366);
        String dayOfYear = String.valueOf(LocalDate.now().getDayOfYear());
        if(!filter.contains(dayOfYear)){
            filter.add(dayOfYear);
        }
        return true;
    }

    public Boolean checkUserIsClockIn(String idNumber){
        String filterName = "customer:clock-in:" + idNumber + ":bloom-filter";
        RedisFilter filter = FilterFactory.createFilter(FilterFactory.FilterType.BLOOM,redisUtil,filterName,1,366);
        String dayOfYear = String.valueOf(LocalDate.now().getDayOfYear());
        boolean isClockIn = filter.contains(dayOfYear);
        return isClockIn;
    }
}
