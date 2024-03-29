package com.xs.DAO.VO.IP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpInfo {
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;
}
