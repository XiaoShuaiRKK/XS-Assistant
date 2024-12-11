package com.xs.assistant.account.service.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.fallback.RemoteCodeFallback;
import com.xs.assistant.account.service.fallback.SystemInfoFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "XS-SYSTEM-MANAGEMENT",path = "/xs_assistant/system/info",fallback = SystemInfoFallback.class)
public interface SystemInfoService {
    @PostMapping("/add")
    ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo);

    @GetMapping("/list")
    ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId);

    @PostMapping("/update")
    ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo);
}
