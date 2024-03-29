package com.xs.assistant.address.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.IP.IpInfo;
import com.xs.assistant.address.Service.IPAddressService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    IPAddressService ipAddressService;
    @Value("${ip.db.path}")
    private String IpDatabasePath;
    @Value("${config.version}")
    private String configVersion;
    @GetMapping("/getAddress")
    public ResponseResult<String> getIpAddressInfo(@RequestParam("ip") String ip){
        return ipAddressService.getAddressByIP(ip);
    }
    @GetMapping("/info/getAddress")
    public ResponseResult<IpInfo> getIpAddressBeanInfo(@RequestParam("ip") String ip){
        return ipAddressService.getAddressBeanByIP(ip);
    }

    @GetMapping("/getConfig")
    public String test(){
        return IpDatabasePath + " : " + configVersion;
    }
}
