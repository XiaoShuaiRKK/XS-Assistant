package com.xs.assistant.gateway.filter;

import com.xs.DAO.ResponseStatus;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.util.Impl.JWTUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class TokenAuthFilter extends BaseFilter implements GlobalFilter, Ordered {

    @Resource
    RedisUtil redisUtil;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(checkReleasePath(exchange.getRequest().getURI().getPath()) != -1) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        String number = JWTUtil.getIDNumber(token);
        if(!Objects.requireNonNull(token)
                .equals(redisUtil.getHash(JWTUtil.JWTKey.REDIS_KEY,number))){
            log.error("Token 验证失败" + token);
            return writeResponse(ResponseStatus.HTTP_STATUS_401,exchange.getResponse(),"Token 验证失败");
        }
        log.info("Token 验证成功" + token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
