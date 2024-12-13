package com.xs.DAO.factory;

import com.xs.DAO.DO.system.SystemInfo;

import java.sql.Timestamp;

public class SystemInfoFactory {
    public static SystemInfo defaultNewSystemInfo(SystemInfo systemInfo,long count) {
        systemInfo.setSysId(systemInfo.getCustomerId() + "S" + String.format("%04d",count));
        systemInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
        systemInfo.setVersion(1);
        return systemInfo;
    }
}
