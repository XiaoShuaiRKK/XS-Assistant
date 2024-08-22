package com.xs.assistant.chat.util;

import com.xs.DAO.DO.chat.ChatGroupMember;
import com.xs.DAO.DO.chat.ChatMember;
import com.xs.DAO.DTO.ChatMessageDTO;
import com.xs.DAO.option.chat.ChatMemberSessionStatus;
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

    public void sendMessage(ChatMember member,String sender,String message) throws IOException {
        String msg = jsonUtil.beanToJson(new ChatMessageDTO(message,member.getMemberId(),sender, LocalDateTime.now()));
        if(member.getStatus() == ChatMemberSessionStatus.ON_LINE)
            member.getSession().sendMessage(new TextMessage(msg));
        else
            member.getMessageBox().push(msg);
    }

    public void sendMessageByGroup(ChatGroupMember member, String message) throws IOException {
        String msg = jsonUtil.beanToJson(new ChatMessageDTO(message, member.getMemberId(),
                member.getGroupId(), LocalDateTime.now()));


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
