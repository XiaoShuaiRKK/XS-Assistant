package com.xs.assistant.address.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.IP.IpInfo;
import com.xs.assistant.address.Service.IPAddressService;
import com.xs.assistant.address.Service.IPAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IPAddressServiceImpl implements IPAddressService {
    @Autowired
    IPAddressUtil ipAddressUtil;
    @Override
    public ResponseResult<String> getAddressByIP(String ip) {
        return ipAddressUtil.getIpInfo(ip).map(ResponseResult::success)
                .orElseGet(() -> ResponseResult.success(null, "获取失败请输入正确的IP"));
    }

    @Override
    public ResponseResult<IpInfo> getAddressBeanByIP(String ip) {
        return ipAddressUtil.getIpInfoByBean(ip).map(ResponseResult::success)
                .orElseGet(() -> ResponseResult.success(null,"获取失败请输入正确的IP"));
    }

}
