package com.xs.assistant.service.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.assistant.service.user.DAO.PointsLevelMapper;
import com.xs.assistant.service.user.service.PointsLevelService;
import org.springframework.stereotype.Service;

@Service
public class PointsLevelServiceImpl implements PointsLevelService {

    final PointsLevelMapper pointsLevelMapper;

    public PointsLevelServiceImpl(PointsLevelMapper pointsLevelMapper) {
        this.pointsLevelMapper = pointsLevelMapper;
    }

    @Override
    public String createPointsLevel(String customerId) {
        String pointsId = new StringBuilder(customerId).insert(2,"P").toString();
        PointsLevel pointsLevel = new PointsLevel(pointsId, 1, 0);
        int insert = pointsLevelMapper.insert(pointsLevel);
        return insert > 0 ? customerId : null;
    }

    @Override
    public PointsLevel getPointsLevel(String pointsLevelId) {
        LambdaQueryWrapper<PointsLevel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PointsLevel::getPointsId, pointsLevelId);
        return pointsLevelMapper.selectOne(queryWrapper);
    }

    public Boolean updatePointsLevel(String pointsLevelId, Integer points) {
        LambdaUpdateWrapper<PointsLevel> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(PointsLevel::getPoints,points)
                .eq(PointsLevel::getPointsId,pointsLevelId);
        return pointsLevelMapper.update(updateWrapper) > 0;
    }
}
