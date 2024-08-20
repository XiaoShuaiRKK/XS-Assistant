package com.xs.assistant.chat.util;

import com.xs.assistant.chat.config.WeSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Component
public class WeSessionMessageUtil {

    private final static Map<String,Stack<String>> MSG_BOX = new HashMap<>();

    public void sendMessage(String to,String message) throws IOException {
        WebSocketSession session = WeSessionManager.get(to);
        if (session != null){
            session.sendMessage(new TextMessage(message));
        }else{
            createMessageBox(to);
            saveMessage(to,message);
        }
    }

    public void createMessageBox(String key){
        if(!MSG_BOX.containsKey(key)){
            MSG_BOX.put(key,new Stack<>());
        }
    }

    public void saveMessage(String key,String msg){
        Stack<String> strings = MSG_BOX.get(key);
        strings.push(msg);
    }

    public List<String> getMessages(String key){
        Stack<String> strings = removeMessageBox(key);
        return new ArrayList<>(strings);
    }

    public boolean checkHasMessages(String key){
        return MSG_BOX.containsKey(key);
    }

    public Stack<String> removeMessageBox(String key){
        return MSG_BOX.remove(key);
    }

}
