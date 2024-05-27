package com.xs.assistant.address.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.IP.IpInfo;
import com.xs.assistant.address.service.IPAddressService;
import com.xs.assistant.address.service.IPAddressUtil;
import org.springframework.stereotype.Service;


@Service
public class IPAddressServiceImpl implements IPAddressService {
    final IPAddressUtil ipAddressUtil;

    public IPAddressServiceImpl(IPAddressUtil ipAddressUtil) {
        this.ipAddressUtil = ipAddressUtil;
    }

    /**
     * 根据ip获取具体的地址
     * @param ip ip
     * @return 地址信息
     */
    @Override
    public ResponseResult<String> getAddressByIP(String ip) {
        return ipAddressUtil.getIpInfo(ip).map(ResponseResult::success)
                .orElseGet(() -> ResponseResult.success(null, "获取失败请输入正确的IP"));
    }

    /**
     * 根据ip获取具体的地址
     * @param ip ip
     * @return 地址信息(实体类)
     */
    @Override
    public ResponseResult<IpInfo> getAddressBeanByIP(String ip) {
        return ipAddressUtil.getIpInfoByBean(ip).map(ResponseResult::success)
                .orElseGet(() -> ResponseResult.success(null,"获取失败请输入正确的IP"));
    }

}
