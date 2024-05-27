package com.xs.assistant.address.service;

import com.xs.DAO.VO.IP.IpInfo;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用ip2region的离线库来获取地址
 */
@RefreshScope
@Component
@Slf4j
public class IPAddressUtil {
    private static final String IP_LOCAL = "127.0.0.1";
    private static final String IP_LOCAL_AREA = "192.168";
    /**
     * 正确ip的正则表达式
     */
    private static final String IP_PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
    @Value("${ip.db.path}")
    private String ipDatabasePath;
    @Value("${config.version}")
    private String configVersion;
    private byte[] dbBinStr;

    private void ipLoad(){
        log.info("config version is:" + configVersion);
        log.info("IP data base path:" + ipDatabasePath);
//        String dbPath = Objects.requireNonNull(IPAddressUtil.class.getResource("/ipdb/ip2region.xdb")).getPath();
        String dbPath = URLDecoder.decode(Objects.requireNonNull(this.getClass().getResource("/")).getPath(),
                StandardCharsets.UTF_8);
        dbPath = dbPath + "/ipdb/ip2region.xdb";
        try{
            dbBinStr = Searcher.loadContentFromFile(dbPath);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 验证IP字符串是否合规
     *
     * @param ip ip字符串
     * @return true标识合规
     */
    public boolean validIp(String ip){
        Pattern r = Pattern.compile(IP_PATTERN);
        Matcher m = r.matcher(ip);
        return m.matches();
    }

    /**
     * 获取地址信息
     * @param ip ip
     * @return 地址信息
     */
    public Optional<String> getIpInfo(String ip){
        if(dbBinStr == null)
            ipLoad();
        String region = null;
        if(validIp(ip)){
            try {
                Searcher searcher = Searcher.newWithBuffer(dbBinStr);
                region = searcher.search(ip);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(region);
    }

    public Optional<IpInfo> getIpInfoByBean(String ip){
        Optional<String> region = getIpInfo(ip);
        if(region.isEmpty())
            return Optional.empty();
        String[] regions = region.get().split("\\|");
        IpInfo info = new IpInfo(regions[0],regions[1],regions[2],regions[3],regions[4]);
        return Optional.of(info);
    }
}
