package com.xs.assistant.chat.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.xs.assistant.chat.remote.UserInfoRemote;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.util.Impl.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MyInterceptor implements HandshakeInterceptor {
    private final UserInfoRemote userInfoRemote;
    public MyInterceptor(UserInfoRemote userInfoRemote) {
        this.userInfoRemote = userInfoRemote;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("握手开始");
        Map<String, String> paramMap = HttpUtil.decodeParamMap(request.getURI().getQuery(), StandardCharsets.UTF_8);
        String uid = paramMap.get("uid");
        String toUid = paramMap.get("to_uid");
        if (CharSequenceUtil.isBlank(uid) || CharSequenceUtil.isBlank(toUid)) {
            log.error("用户 token : " + uid + " 握手失败");
            return false;
        }
        if(userInfoRemote.checkCustomerByID(uid).getData() && userInfoRemote.checkCustomerByID(toUid).getData()){
            attributes.put("uid",uid);
            attributes.put("to_uid",toUid);
            log.info("用户 token : " + uid + " 握手成功");
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("握手完成");
    }
}
