package com.xs.assistant.chat.handler;

import com.xs.DAO.DO.chat.ChatGroup;
import com.xs.DAO.DO.chat.ChatGroupMember;
import com.xs.DAO.DO.chat.ChatMember;
import com.xs.DAO.DTO.ChatMessageDTO;
import com.xs.DAO.option.chat.ChatGroupMemberTypeEnum;
import com.xs.DAO.option.chat.ChatMemberSessionStatus;
import com.xs.assistant.chat.config.GroupSessionManager;
import com.xs.assistant.chat.config.WeSessionManager;
import com.xs.assistant.chat.util.WeSessionMessageUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

@Component
public class GroupAuthHandler extends TextWebSocketHandler {

    final GroupSessionManager groupSessionManager;
    final WeSessionMessageUtil sendManager;

    public GroupAuthHandler(GroupSessionManager groupSessionManager, WeSessionMessageUtil sendManager) {
        this.groupSessionManager = groupSessionManager;
        this.sendManager = sendManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String token = session.getAttributes().get("token").toString();
        String group = session.getAttributes().get("group").toString();
        String groupId = session.getAttributes().get("groupId").toString();
        if(StringUtil.isNullOrEmpty(groupId)){
            ChatGroup groupAndJoin = groupSessionManager.createGroupAndJoin(token, session, group);
            sendManager.sendMessage(new ChatMember(token,groupId,session,
                    ChatMemberSessionStatus.ON_LINE,new Stack<>()),"Server",groupAndJoin.getGroupId());
            return;
        }
        if(!groupSessionManager.checkGroup(groupId)){
            sendManager.sendMessage(new ChatMember(token,groupId,session,
                    ChatMemberSessionStatus.ON_LINE,new Stack<>()),"Server","不存在群聊");
            return;
        }
        if(!groupSessionManager.checkMemberJoined(groupId,token))
            groupSessionManager.addMember(groupId,token,session);
        else
            groupSessionManager.onlineMember(groupId,token);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Object token = session.getAttributes().get("token");
        Object group = session.getAttributes().get("groupId");
        String msg = message.getPayload();
        if(token != null && group != null){
            String groupInfo = group.toString();
            String tokenInfo = token.toString();
            Map<String, ChatGroupMember> groupMember = groupSessionManager.getGroupMember(groupInfo);
            ChatGroupMember member = new ChatGroupMember();
            member.setMemberId(tokenInfo);
            groupMember.forEach((k,v) -> {
                try {
                    if(!v.equals(member))
                        sendManager.sendMessage(v,tokenInfo,msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        Object group = session.getAttributes().get("group");
        groupSessionManager.leaveMember(group.toString(),token.toString());
    }
}
