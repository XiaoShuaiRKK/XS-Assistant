package com.xs.assistant.chat.config;

import com.xs.DAO.DO.chat.ChatGroup;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GroupSessionManager {

    final UIDCodeUtil codeUtil;

    private static final ConcurrentHashMap<ChatGroup, Map<String,WebSocketSession>> GROUP_POOL = new ConcurrentHashMap<>();

    public GroupSessionManager(UIDCodeUtil codeUtil) {
        this.codeUtil = codeUtil;
    }

    public Optional<ChatGroup> createGroup(String leaderId, String groupName){
        ChatGroup group = ChatGroup.builder().leaderId(leaderId).build();
        if(!GROUP_POOL.containsKey(group)){
            group.setGroupId(codeUtil.createCode(UIDCodeUtil.CreateCodeType.CHAT_GROUP,GROUP_POOL.mappingCount()));
            group.setGroupName(groupName);
            group.setCreateTime(LocalDateTime.now());
            GROUP_POOL.put(group,new HashMap<>());
            return Optional.of(group);
        }
        return Optional.empty();
    }

    public void addUser(String groupId,String memberId,WebSocketSession session){
        getGroupMember(groupId).put(memberId,session);
    }

    public WebSocketSession removeMember(String groupId){
        return getGroupMember(groupId).remove(groupId);
    }

    public Map<String,WebSocketSession> getGroupMember(String groupId){
        return GROUP_POOL.get(ChatGroup.builder().groupId(groupId).build());
    }

    public Map<String,WebSocketSession> delectGroup(String groupId){
        return GROUP_POOL.remove(ChatGroup.builder().groupId(groupId).build());
    }
}
