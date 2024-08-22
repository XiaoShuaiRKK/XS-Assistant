package com.xs.assistant.chat.config;

import com.xs.DAO.DO.chat.ChatMember;
import com.xs.DAO.option.chat.ChatMemberSessionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class WeSessionManager {
    private final static ConcurrentMap<String, ChatMember> SESSION_POOL = new ConcurrentHashMap<>();

    public static void add(String sender,String to,WebSocketSession session){
        SESSION_POOL.put(sender,new ChatMember(sender,to,session,
                ChatMemberSessionStatus.ON_LINE,new Stack<>()));
    }

    public static ChatMember remove(String key){
        return SESSION_POOL.remove(key);
    }

    public static void removeAndClose(String key) throws IOException {
        ChatMember member = remove(key);
        if (member != null){
            member.getSession().close();
        }
    }

    public static ChatMember get(String key){
        return SESSION_POOL.get(key);
    }
}
