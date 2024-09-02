package com.xs.assistant.chat.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.xs.assistant.chat.config.GroupSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j
public class GroupInterceptor implements HandshakeInterceptor {

    final GroupSessionManager groupSessionManager;

    public GroupInterceptor(GroupSessionManager groupSessionManager) {
        this.groupSessionManager = groupSessionManager;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        Map<String, String> paramMap = HttpUtil.decodeParamMap(request.getURI().getQuery(), StandardCharsets.UTF_8);
        String uid = paramMap.get("token");
        String group = paramMap.get("group");
        String groupId = paramMap.get("groupId");
        if (CharSequenceUtil.isNotBlank(uid)){
            attributes.put("token",uid);
            attributes.put("group",group);
            attributes.put("groupId",groupId);
            log.info(uid + " :  握手成功");
            return true;
        }
        log.error(uid + " : 握手失败");
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
