package com.xs.assistant.gateway.Filter;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.ResponseStatus;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.Impl.JWTUtil;
import com.xs.assistant.util.Impl.JsonUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
public class TokenAuthFilter implements GlobalFilter, Ordered {
    @Resource
    RedisUtil redisUtil;
    @Resource
    JsonUtil jsonUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(checkPath(exchange.getRequest().getURI().getPath()))
            return chain.filter(exchange);
        String token = exchange.getRequest().getHeaders().getFirst("token");
        String number = JWTUtil.getIDNumber(token);
        if(!Objects.requireNonNull(token)
                .equals(redisUtil.getHash(JWTUtil.JWTKey.REDIS_KEY,number))){
            log.error("Token 验证失败" + token);
            return writeResponse(exchange.getResponse(),"Token 验证失败");
        }
        log.info("Token 验证成功" + token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    protected Mono<Void> writeResponse(ServerHttpResponse response, String msg){
       String json = jsonUtil.beanToJson(new ResponseResult<Object>(System.currentTimeMillis(),
               ResponseStatus.HTTP_STATUS_401.getResponseCode(),null,msg));
       byte[] bits = json.getBytes(StandardCharsets.UTF_8);
       DataBuffer buffer = response.bufferFactory().wrap(bits);
       response.setStatusCode(HttpStatus.OK);
       response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
       return response.writeWith(Mono.just(buffer));
    }

    private boolean checkPath(String path){
        return switch (path) {
            case "/xs_assistant/account/login",
                    "/xs_assistant/account/sendCode",
                    "/xs_assistant/account/register" -> true;
            default -> false;
        };
    }
}
