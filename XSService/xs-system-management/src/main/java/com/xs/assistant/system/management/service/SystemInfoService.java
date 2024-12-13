package com.xs.assistant.system.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.assistant.system.management.DAO.SystemInfoMapper;

public interface SystemInfoService extends IService<SystemInfo> {
    Long getCountByCustomerId(String customerId);
    Boolean checkCustomerDevice(String customerId, String deviceName);
    Boolean updateDeviceLoginLastTime(String customerId,String deviceName,String system);
}
