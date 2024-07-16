package com.xs.assistant.gateway.filter;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.ResponseStatus;
import com.xs.assistant.util.Impl.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class BaseFilter {
    @Resource
    JsonUtil jsonUtil;

    protected byte checkReleasePath(String path){
        return switch (path) {
            case "/xs_assistant/account/login" -> 0;
            case "/xs_assistant/account/sendCode",
                    "/xs_assistant/account/register" -> 1;
            case "/xs_assistant/admin/create/admin" -> 2;
            default -> -1;
        };
    }

    protected Mono<Void> writeResponse(ResponseStatus httpStatus,ServerHttpResponse response, String msg){
        String json = jsonUtil.beanToJson(new ResponseResult<Object>(System.currentTimeMillis(),
                httpStatus.getResponseCode(),null,msg));
        byte[] bits = json.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
