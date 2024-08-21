package com.xs.assistant.chat.util;

import com.xs.DAO.DTO.ChatMessageDTO;
import com.xs.assistant.chat.config.WeSessionManager;
import com.xs.assistant.util.Impl.JsonUtil;
import jakarta.json.Json;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class WeSessionMessageUtil {

    final JsonUtil jsonUtil;

    private final static Map<String,Stack<String>> MSG_BOX = new HashMap<>();

    public WeSessionMessageUtil(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    public void sendMessage(String sender,String to,String message) throws IOException {
        WebSocketSession session = WeSessionManager.get(to);
        String msg = jsonUtil.beanToJson(new ChatMessageDTO(message,sender,to, LocalDateTime.now()));
        if (session != null){
            session.sendMessage(new TextMessage(msg));
        }else{
            createMessageBox(to);
            saveMessage(to,msg);
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
