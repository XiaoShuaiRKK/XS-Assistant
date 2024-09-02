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

@Component
@Slf4j
public class WeSessionManager {
    private static final ConcurrentMap<String, ChatMember> SESSION_POOL = new ConcurrentHashMap<>();

    public void add(String sender,String to,WebSocketSession session){
        SESSION_POOL.put(sender,new ChatMember(sender,to,session,
                ChatMemberSessionStatus.ON_LINE,new Stack<>()));
    }

    public ChatMember remove(String key){
        return SESSION_POOL.remove(key);
    }

    public void removeAndClose(String key) throws IOException {
        ChatMember member = remove(key);
        if (member != null){
            member.getSession().close();
        }
    }

    public void onLineMember(String token,WebSocketSession session){
        ChatMember member = get(token);
        member.setSession(session);
        member.setStatus(ChatMemberSessionStatus.ON_LINE);
    }

    public void offLineMember(String token) {
        ChatMember member = SESSION_POOL.get(token);
        member.setSession(null);
        member.setStatus(ChatMemberSessionStatus.OFF_LINE);
    }

    public boolean containsMember(String token){
        return SESSION_POOL.containsKey(token);
    }

    public ChatMember get(String key){
        return SESSION_POOL.get(key);
    }
}
