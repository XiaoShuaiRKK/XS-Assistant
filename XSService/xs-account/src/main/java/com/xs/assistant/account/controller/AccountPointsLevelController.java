package com.xs.assistant.account.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.controller.api.AccountPointsLevelApi;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountPointsLevelService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/points")
public class AccountPointsLevelController implements AccountPointsLevelApi {
    final AccountPointsLevelService accountPointsLevelService;
    final AccountInfoService accountInfoService;

    public AccountPointsLevelController(@Qualifier("userPointsLevelRemote") AccountPointsLevelService accountPointsLevelService, AccountInfoService accountInfoService) {
        this.accountPointsLevelService = accountPointsLevelService;
        this.accountInfoService = accountInfoService;
    }

    @GetMapping("/clock/in")
    public ResponseResult<Boolean> clockInPoints(@RequestParam("id_number")String idNumber){
        if(!accountInfoService.checkCustomerIsClockIn(idNumber).getData()){
            String pointLevelId = accountInfoService.getPointsLevelIdNumber(idNumber).getData();
            accountInfoService.customerClockInByIdNumber(idNumber);
            accountPointsLevelService.clockInPoints(pointLevelId);
            return ResponseResult.success(true,"签到成功,经验加2");
        }
        return ResponseResult.success(false,"今日已签到");
    }

    @GetMapping("/clock/check")
    public ResponseResult<Boolean> checkIsClockIn(@RequestParam("id_number") String idNumber){
        return accountInfoService.checkCustomerIsClockIn(idNumber);
    }

    @GetMapping("/increment")
    public ResponseResult<Boolean> incrementPoints(@RequestParam("id_number")String idNumber,
                                                   @RequestParam("increment_points")Integer incrementPoints){
        String pointLevelId = accountInfoService.getPointsLevelIdNumber(idNumber).getData();
        return accountPointsLevelService.incrementPoints(pointLevelId, incrementPoints);
    }
}
