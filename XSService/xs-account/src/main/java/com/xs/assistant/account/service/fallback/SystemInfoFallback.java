package com.xs.assistant.account.service.fallback;

import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.remote.SystemInfoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class SystemInfoFallback implements SystemInfoService {
    @Override
    public ResponseResult<Boolean> addSystemInfo(SystemInfo systemInfo) {
        return ResponseResult.unavailable("设备信息系统正忙");
    }

    @Override
    public ResponseResult<List<SystemInfo>> getAllSystemInfo(String customerId) {
        return ResponseResult.unavailable("设备信息系统正忙");
    }

    @Override
    public ResponseResult<Boolean> updateSystemInfo(SystemInfo systemInfo) {
        return ResponseResult.unavailable("设备信息系统正忙");
    }
}
