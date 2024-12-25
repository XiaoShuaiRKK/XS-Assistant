package com.xs.assistant.account.service.remote;

import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.fallback.PointsLevelFallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "userPointsLevelRemote",value = "XS-SERVICE-USER",path = "/xs_assistant/points",fallback = PointsLevelFallback.class)
@Qualifier("userPointsLevelRemote")
public interface AccountPointsLevelService {
    @GetMapping("/getById")
    ResponseResult<PointsLevel> getPointsLevel(@RequestParam("points_level_id") String pointsLevelId);
    @GetMapping("/clockIn")
    ResponseResult<Boolean> clockInPoints(@RequestParam("points_level_id")String pointsLevelId);

    @GetMapping("/increment")
    ResponseResult<Boolean> incrementPoints(@RequestParam("points_level_id")String pointsLevelId,
                                                   @RequestParam("increment_points")Integer incrementPoints);
}
