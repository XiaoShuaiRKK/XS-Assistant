package com.xs.assistant.system.management.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.DAO.DO.system.SystemInfo;
import com.xs.assistant.system.management.DAO.SystemInfoMapper;
import com.xs.assistant.system.management.service.SystemInfoService;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo> implements SystemInfoService {
}
