package com.xs.assistant.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.controller.api.AccountDeviceApi;
import com.xs.assistant.account.service.remote.SystemInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class AccountDeviceController implements AccountDeviceApi {
    final SystemInfoService systemInfoService;

    public AccountDeviceController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @Operation(summary = "添加设备接口")
    @PostMapping("/add")
    public ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.addSystemInfo(systemInfo);
    }

    @Operation(summary = "用户设备列表接口")
    @GetMapping("/list")
    public ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId) {
        return systemInfoService.getAllSystemInfo(customerId);
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo) {
        return systemInfoService.updateSystemInfo(systemInfo);
    }
}
