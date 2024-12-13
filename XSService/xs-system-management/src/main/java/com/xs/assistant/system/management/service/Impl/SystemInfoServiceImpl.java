package com.xs.assistant.system.management.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.assistant.system.management.DAO.SystemInfoMapper;
import com.xs.assistant.system.management.service.SystemInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo> implements SystemInfoService {
    @Override
    public Long getCountByCustomerId(String customerId) {
        LambdaQueryWrapper<SystemInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfo::getCustomerId, customerId);
        return this.count(wrapper);
    }

    @Override
    public Boolean checkCustomerDevice(String customerId, String deviceName) {
        LambdaQueryWrapper<SystemInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfo::getCustomerId, customerId);
        wrapper.eq(SystemInfo::getComputerName,deviceName);
        return this.count(wrapper) == 0;
    }

    @Override
    public Boolean updateDeviceLoginLastTime(String customerId, String deviceName, String system) {
        LambdaUpdateWrapper<SystemInfo> updateWrapper = new UpdateWrapper<SystemInfo>().lambda();
        updateWrapper.set(SystemInfo::getLastTime,new Timestamp(System.currentTimeMillis()))
                .eq(SystemInfo::getCustomerId,customerId)
                .eq(SystemInfo::getComputerName,deviceName)
                .eq(SystemInfo::getSystem,system);
        return this.update(updateWrapper);
    }
}
