package com.xs.assistant.system.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.factory.SystemInfoFactory;
import com.xs.assistant.system.management.service.SystemInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/info")
public class SystemInfoController {
    final SystemInfoService systemInfoService;

    public SystemInfoController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        boolean result;
        if(Boolean.FALSE.equals(systemInfoService.checkCustomerDevice(systemInfo.getCustomerId(),systemInfo.getComputerName()))){
            result = systemInfoService.updateDeviceLoginLastTime(systemInfo.getCustomerId(), systemInfo.getComputerName(), systemInfo.getSystem());
            return ResponseResult.success(result);
        }
        long count = systemInfoService.getCountByCustomerId(systemInfo.getCustomerId());
        SystemInfoFactory.defaultNewSystemInfo(systemInfo, count);
        log.info("Device : " + systemInfo);
        result = systemInfoService.save(systemInfo);
        return ResponseResult.success(result);
    }

    @GetMapping("/list")
    public ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId) {
        LambdaQueryWrapper<SystemInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfo::getCustomerId, customerId);
        return ResponseResult.success(systemInfoService.list(wrapper));
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo) {
        systemInfo.setVersion(systemInfo.getVersion() + 1);
        systemInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
        return ResponseResult.success(systemInfoService.updateById(systemInfo));
    }
}
