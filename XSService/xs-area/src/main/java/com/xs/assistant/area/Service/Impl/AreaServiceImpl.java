package com.xs.assistant.area.Service.Impl;

import com.xs.DAO.DO.Area.Area;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.area.DAO.AreaCachePool;
import com.xs.assistant.area.DAO.AreaRepository;
import com.xs.assistant.area.Service.AreaService;
import com.xs.assistant.redis.Aspect.Annotation.RedisSet;
import com.xs.assistant.redis.Aspect.Annotation.RedisSetList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    AreaCachePool areaCachePool;

    private static final String REDIS_KEY_AREA = "area:";

    @Override
    public List<Area> getAllArea() {
        if(!areaCachePool.isEmpty())
            return areaCachePool.getAreas();
        return areaCachePool.setAreas(selectAllArea());
    }

    @Override
    public Area getArea(Integer id) {
        if(!areaCachePool.isEmpty())
            return areaCachePool.getAreas().get(id - 1);
        return areaRepository.selectAreaById(id);
    }

    @RedisSetList(key = REDIS_KEY_AREA,time = 30,timeStyle = TimeUnit.DAYS)
    private List<Area> selectAllArea(){
        return areaCachePool.setAreas(areaRepository.selectAll());
    }


}
