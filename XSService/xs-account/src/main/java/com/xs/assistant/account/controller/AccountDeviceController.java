package com.xs.assistant.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.remote.SystemInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class AccountDeviceController {
    final SystemInfoService systemInfoService;

    public AccountDeviceController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.addSystemInfo(systemInfo);
    }

    @GetMapping("/list")
    public ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId) {
        return systemInfoService.getAllSystemInfo(customerId);
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.updateSystemInfo(systemInfo);
    }
}
