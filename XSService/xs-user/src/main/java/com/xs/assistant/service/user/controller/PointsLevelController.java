package com.xs.assistant.service.user.controller;

import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.service.user.service.PointsLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
@Slf4j
public class PointsLevelController {
    final PointsLevelService pointsLevelService;

    public PointsLevelController(PointsLevelService pointsLevelService) {
        this.pointsLevelService = pointsLevelService;
    }

    @GetMapping("/getById")
    public ResponseResult<PointsLevel> getPointsLevel(@RequestParam("points_level_id") String pointsLevelId){
        return ResponseResult.success(pointsLevelService.getPointsLevel(pointsLevelId));
    }

    @GetMapping("/clockIn")
    public ResponseResult<Boolean> clockInPoints(@RequestParam("points_level_id")String pointsLevelId){
        return ResponseResult.success(pointsLevelService.increasePointsLevel(pointsLevelId,2));
    }

    @GetMapping("/increment")
    public ResponseResult<Boolean> incrementPoints(@RequestParam("points_level_id")String pointsLevelId,
                                             @RequestParam("increment_points")Integer incrementPoints){
        return ResponseResult.success(pointsLevelService.updatePointsLevel(pointsLevelId,incrementPoints));
    }
}
