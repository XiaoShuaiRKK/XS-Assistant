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

    private static final String PARAM_UID = "uid";
    private static final String PARAM_TO_UID = "to_uid";

    final WeSessionManager weSessionManager;
    final WeSessionMessageUtil weSessionMessageUtil;

    public HttpAuthHandler(WeSessionManager weSessionManager, WeSessionMessageUtil weSessionMessageUtil) {
        this.weSessionManager = weSessionManager;
        this.weSessionMessageUtil = weSessionMessageUtil;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        Object token = session.getAttributes().get(PARAM_UID);
        Object toToken = session.getAttributes().get(PARAM_TO_UID);
        if(token != null && toToken != null){
            String tokenStr = token.toString();
            String toTokenStr = toToken.toString();
            if(!weSessionManager.containsMember(tokenStr)){
                weSessionManager.add(tokenStr,toTokenStr,session);
                weSessionMessageUtil.sendMessage(session, LocalDateTime.now().toString());
                return;
            }
            weSessionManager.onLineMember(tokenStr,session);
            weSessionManager.get(tokenStr).getMessageBox().forEach(msg -> {
                try {
                    session.sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            weSessionMessageUtil.sendMessage(session,"错误访问");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        Object token = session.getAttributes().get(PARAM_UID);
        Object toToken = session.getAttributes().get(PARAM_TO_UID);
        log.info("Sever : " + token + " ----- Message : " + msg);
        weSessionMessageUtil.sendMessage(weSessionManager.get(toToken.toString()),token.toString(),msg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get(PARAM_UID);
        Object toToken = session.getAttributes().get(PARAM_TO_UID);
        weSessionMessageUtil.sendMessage(weSessionManager.get(toToken.toString()),
                token.toString(),token + " has been taken offline");
        weSessionManager.offLineMember(token.toString());
    }
}
