package com.xs.assistant.service.user.service;

import com.xs.assistant.redis.filter.FilterFactory;
import com.xs.assistant.redis.filter.RedisFilter;

import java.time.LocalDate;

public interface UserClockInService {
    Boolean clockInUser(String idNumber);

    Boolean checkUserIsClockIn(String idNumber);
}
