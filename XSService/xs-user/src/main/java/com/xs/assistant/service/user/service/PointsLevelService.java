package com.xs.assistant.service.user.service;

import com.xs.DAO.DO.customer.PointsLevel;

public interface PointsLevelService {
    String createPointsLevel(String customerId);
    PointsLevel getPointsLevel(String pointsLevelId);
    Boolean updatePointsLevel(String pointsLevelId, Integer points);
}
