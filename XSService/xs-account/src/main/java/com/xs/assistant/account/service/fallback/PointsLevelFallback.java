package com.xs.assistant.account.service.fallback;

import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.remote.AccountPointsLevelService;
import org.springframework.stereotype.Component;

@Component
public class PointsLevelFallback implements AccountPointsLevelService {
    @Override
    public ResponseResult<PointsLevel> getPointsLevel(String pointsLevelId) {
        return ResponseResult.unavailable("经验信息系统正忙");
    }

    @Override
    public ResponseResult<Boolean> clockInPoints(String pointsLevelId) {
        return ResponseResult.unavailable("经验信息系统正忙");
    }

    @Override
    public ResponseResult<Boolean> incrementPoints(String pointsLevelId, Integer incrementPoints) {
        return ResponseResult.unavailable("经验信息系统正忙");
    }
}
