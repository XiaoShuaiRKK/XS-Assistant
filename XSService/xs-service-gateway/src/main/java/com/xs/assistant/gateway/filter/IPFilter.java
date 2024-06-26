package com.xs.assistant.gateway.filter;

import com.xs.DAO.ResponseStatus;
import com.xs.assistant.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class IPFilter extends BaseFilter implements GlobalFilter, Ordered {
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String LOCALHOST_PHYSICS = "0:0:0:0:0:0:0:1";
    private static final String SEPARATOR = ",";

    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String REDIS_IP_LOGIN_KEY = "loginIp:";
    private static final String REDIS_IP_KEY = "ip:";
    private static final String REDIS_IP_TMP_BLACK_LIST_KEY = "tmpBlackIp:";
    @Autowired
    RedisUtil redisUtil;

    /**
     * 判断IP请求次数过多拦截
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = getRealIpAddress(exchange.getRequest());
        if(checkReleasePath(exchange.getRequest().getURI().getPath()) == 0){
            String key = REDIS_IP_LOGIN_KEY + ip;
            Integer count = (Integer) redisUtil.get(key);
            if(count == null)
                redisUtil.set(key,1);
            else if(count < 5){
                redisUtil.setIfPresent(key,count + 1);
            }
            else
                return writeResponse(ResponseStatus.HTTP_STATUS_429, exchange.getResponse(),"登录次数太过频繁，请一分钟后重试");
        }
        String key = REDIS_IP_KEY + ip;
        String black = REDIS_IP_TMP_BLACK_LIST_KEY + ip;
        if(!redisUtil.hasKey(key)){
            redisUtil.set(key,1,20L);
            return chain.filter(exchange);
        }
        int count = (Integer) redisUtil.get(key);
        if(count > 100){
            redisUtil.set(black,1,120L);
            return writeResponse(ResponseStatus.HTTP_STATUS_429,exchange.getResponse(),"请求太频繁请稍后重试");
        }else if(redisUtil.hasKey(black)){
            return writeResponse(ResponseStatus.HTTP_STATUS_429,exchange.getResponse(),"请求太频繁请稍后重试");
        }
        redisUtil.set(key,count + 1,20L);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

//    private Long hashIncrementValue(String key,String field){
//
//    }

    /**
     * 获取真实ip地址
     * @param serverHttpRequest
     * @return
     */
    public static String getRealIpAddress(ServerHttpRequest serverHttpRequest){
        String ipAddress;
        try {
            ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_X_FORWARDED_FOR);
            if(StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress))
                ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_PROXY_CLIENT_IP);
            if(StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress))
                ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_WL_PROXY_CLIENT_IP);
            if(StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)){
                InetSocketAddress inetSocketAddress = serverHttpRequest.getRemoteAddress();
                if(inetSocketAddress != null)
                    ipAddress = inetSocketAddress.getAddress().getHostAddress();
                if(LOCALHOST.equalsIgnoreCase(ipAddress) || LOCALHOST_PHYSICS.equalsIgnoreCase(ipAddress))
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
            }
            if(ipAddress != null)
                ipAddress = ipAddress.split(SEPARATOR)[0].trim();
        }catch (Exception e){
            log.error("解析请求IP失败 : ",e);
            ipAddress = "";
        }
        return ipAddress == null ? "" : ipAddress;
    }
}
