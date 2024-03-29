package com.xs.assistant.address.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.IP.IpInfo;

public interface IPAddressService {
    ResponseResult<String> getAddressByIP(String ip);
    ResponseResult<IpInfo> getAddressBeanByIP(String ip);
}
