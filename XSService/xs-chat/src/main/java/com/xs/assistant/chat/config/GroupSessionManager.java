package com.xs.assistant.chat.config;

import com.xs.DAO.DO.chat.ChatGroup;
import com.xs.DAO.DO.chat.ChatGroupMember;
import com.xs.DAO.option.chat.ChatGroupMemberTypeEnum;
import com.xs.DAO.option.chat.ChatMemberSessionStatus;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GroupSessionManager {

    final UIDCodeUtil codeUtil;

    private static final ConcurrentHashMap<ChatGroup, Map<String, ChatGroupMember>> GROUP_POOL = new ConcurrentHashMap<>();
    private static final HashMap<String,String> TEMP_ID_POOL = new HashMap<>();

    public GroupSessionManager(UIDCodeUtil codeUtil) {
        this.codeUtil = codeUtil;
    }

    public boolean checkGroup(String groupId){
        return GROUP_POOL.containsKey(ChatGroup.builder().groupId(groupId).build());
    }

    public boolean checkMemberJoined(String groupId,String memberId){
        return GROUP_POOL.get(ChatGroup.builder().groupId(groupId).build()).containsKey(memberId);
    }

    public ChatGroup createGroupAndJoin(String leaderId,WebSocketSession leaderSession, String groupName){
        ChatGroup group = ChatGroup.builder().leaderId(leaderId)
                .groupId(codeUtil.createCode(UIDCodeUtil.CreateCodeType.CHAT_GROUP,GROUP_POOL.mappingCount()))
                .groupName(groupName)
                .createTime(LocalDateTime.now())
                .build();
        GROUP_POOL.put(group,new HashMap<>());
        TEMP_ID_POOL.put(group.getGroupName(),group.getGroupId());
        addMemberByType(group.getGroupId(),leaderId,leaderSession,ChatGroupMemberTypeEnum.LEADER);
        return group;
    }

    public void addMember(String groupId,String memberId,WebSocketSession session){
        addMemberByType(groupId,memberId,session,ChatGroupMemberTypeEnum.MEMBER);
    }

    public void addMemberByType(String groupId, String memberId, WebSocketSession session, ChatGroupMemberTypeEnum type){
        getGroupMember(groupId).put(memberId,new ChatGroupMember(memberId,groupId,session,
                ChatMemberSessionStatus.ON_LINE,type,new Stack<>()));
    }

    public void leaveLeader(String groupName,String memberId){
        String groupId = TEMP_ID_POOL.get(groupName);
        ChatGroupMember leader = getGroupMember(groupId).get(memberId);
        try {
            leader.getSession().close();
            leader.setStatus(ChatMemberSessionStatus.OFF_LINE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TEMP_ID_POOL.remove(groupName);
    }

    public void leaveMember(String groupId,String memberId){
        Map<String, ChatGroupMember> group = getGroupMember(groupId);
        ChatGroupMember member = group.get(memberId);
        try {
            member.getSession().close();
            member.setStatus(ChatMemberSessionStatus.OFF_LINE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatGroupMember onlineMember(String groupId,String memberId,WebSocketSession session){
        ChatGroupMember member = getGroupMember(groupId).get(memberId);
        member.setSession(session);
        member.setStatus(ChatMemberSessionStatus.ON_LINE);
        return member;
    }

    public ChatGroupMember removeMember(String groupId,String memberId){
        Map<String, ChatGroupMember> group = getGroupMember(groupId);
        ChatGroupMember member = group.remove(memberId);
        if(group.isEmpty())
            deleteGroup(groupId);
        return member;
    }

    public Map<String,ChatGroupMember> getGroupMember(String groupId){
        return GROUP_POOL.get(ChatGroup.builder().groupId(groupId).build());
    }

    public Map<String,ChatGroupMember> deleteGroup(String groupId){
        return GROUP_POOL.remove(ChatGroup.builder().groupId(groupId).build());
    }
}
