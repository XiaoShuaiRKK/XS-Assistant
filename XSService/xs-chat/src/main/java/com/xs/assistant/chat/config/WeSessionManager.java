package com.xs.assistant.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class WeSessionManager {
    private final static ConcurrentMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    public static void add(String key,WebSocketSession session){
        SESSION_POOL.put(key,session);
    }

    public static WebSocketSession remove(String key){
        return SESSION_POOL.remove(key);
    }

    public static void removeAndClose(String key) throws IOException {
        WebSocketSession session = remove(key);
        if (session != null){
            session.close();
        }
    }

    public static WebSocketSession get(String key){
        return SESSION_POOL.get(key);
    }
}
