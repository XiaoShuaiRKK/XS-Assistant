package com.xs.assistant.address.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.IP.IpInfo;
import com.xs.assistant.address.service.IPAddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/ip")
public class IPAddressController {
    final IPAddressService ipAddressService;
    @Value("${ip.db.path}")
    private String IpDatabasePath;
    @Value("${config.version}")
    private String configVersion;

    public IPAddressController(IPAddressService ipAddressService) {
        this.ipAddressService = ipAddressService;
    }

    /**
     * 根据ip来获取地址信息
     * @param ip ip
     * @return 地址信息
     */
    @GetMapping("/getAddress")
    public ResponseResult<String> getIpAddressInfo(@RequestParam("ip") String ip){
        return ipAddressService.getAddressByIP(ip);
    }

    /**
     * 根据ip来获取地址信息
     * @param ip ip
     * @return 地址信息(实体类)
     */
    @GetMapping("/info/getAddress")
    public ResponseResult<IpInfo> getIpAddressBeanInfo(@RequestParam("ip") String ip){
        return ipAddressService.getAddressBeanByIP(ip);
    }
}
