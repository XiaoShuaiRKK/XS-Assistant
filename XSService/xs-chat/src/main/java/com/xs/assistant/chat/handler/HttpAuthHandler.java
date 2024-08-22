package com.xs.assistant.chat.handler;

import com.xs.assistant.chat.config.WeSessionManager;
import com.xs.assistant.chat.config.WebSocketConfig;
import com.xs.assistant.chat.util.WeSessionMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class HttpAuthHandler extends TextWebSocketHandler {

    final WeSessionMessageUtil weSessionMessageUtil;

    public HttpAuthHandler(WeSessionMessageUtil weSessionMessageUtil) {
        this.weSessionMessageUtil = weSessionMessageUtil;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Object token = session.getAttributes().get("token");
        Object toToken = session.getAttributes().get("to");
        if(token != null && toToken != null){
            String tokenStr = token.toString();
            String toTokenStr = toToken.toString();
            WeSessionManager.add(tokenStr,toTokenStr,session);
            if(weSessionMessageUtil.checkHasMessages(tokenStr)){
                weSessionMessageUtil.getMessages(tokenStr).forEach(msg -> {
                    try {
                        session.sendMessage(new TextMessage(msg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        else
            throw new RuntimeException("用户登录已经失效");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        Object token = session.getAttributes().get("token");
        Object toToken = session.getAttributes().get("to");
        log.info("Sever : " + token + " ----- Message : " + msg);
        weSessionMessageUtil.sendMessage(WeSessionManager.get(toToken.toString()),token.toString(),msg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        Object toToken = session.getAttributes().get("to");
        weSessionMessageUtil.sendMessage(WeSessionManager.get(toToken.toString()),
                token.toString(),token + " has been taken offline");
        WeSessionManager.remove(token.toString());
    }
}
