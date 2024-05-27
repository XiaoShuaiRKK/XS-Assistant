package com.xs.assistant.area.service.Impl;

import com.xs.DAO.DO.Area.Area;
import com.xs.assistant.area.DAO.AreaCachePool;
import com.xs.assistant.area.DAO.AreaRepository;
import com.xs.assistant.area.service.AreaService;
import com.xs.assistant.redis.aspect.Annotation.RedisSetList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {
    final AreaRepository areaRepository;
    final AreaCachePool areaCachePool;

    /**
     * area redis key
     */
    private static final String REDIS_KEY_AREA = "area:";

    public AreaServiceImpl(AreaRepository areaRepository, AreaCachePool areaCachePool) {
        this.areaRepository = areaRepository;
        this.areaCachePool = areaCachePool;
    }

    /**
     * 获取所有国家
     * @return 国家列表
     */
    @Override
    public List<Area> getAllArea() {
        if(!areaCachePool.isEmpty())
            return areaCachePool.getAreas();
        return areaCachePool.setAreas(selectAllArea());
    }

    /**
     * 根据id获取国家
     * @param id id
     * @return 国家
     */
    @Override
    public Area getArea(Integer id) {
        if(!areaCachePool.isEmpty())
            return areaCachePool.getAreas().get(id - 1);
        return areaRepository.selectAreaById(id);
    }

    /**
     * 查询所有国家
     * 查询后会存入redis 方便下次查询时获取
     * @return 国家列表
     */
    @RedisSetList(key = REDIS_KEY_AREA,time = 30,timeStyle = TimeUnit.DAYS)
    private List<Area> selectAllArea(){
        return areaCachePool.setAreas(areaRepository.selectAll());
    }


}
