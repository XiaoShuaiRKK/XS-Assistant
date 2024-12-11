package com.xs.assistant.system.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.system.management.service.SystemInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class SystemInfoController {
    final SystemInfoService systemInfoService;

    public SystemInfoController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        LambdaQueryWrapper<SystemInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfo::getCustomerId, systemInfo.getCustomerId());
        long count = systemInfoService.count(wrapper);
        systemInfo.setSysId(systemInfo.getCustomerId() + String.format("%04d",count));
        boolean save = systemInfoService.save(systemInfo);
        return ResponseResult.success(save);
    }

    @GetMapping("/list")
    public ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId) {
        LambdaQueryWrapper<SystemInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfo::getCustomerId, customerId);
        return ResponseResult.success(systemInfoService.list(wrapper));
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo) {
        return ResponseResult.success(systemInfoService.updateById(systemInfo));
    }
}
