package com.xs.DAO.DO.chat;

import com.xs.DAO.option.chat.ChatGroupMemberTypeEnum;
import com.xs.DAO.option.chat.ChatMemberSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;
import java.util.Stack;

@Data
@NoArgsConstructor
public class ChatGroupMember extends ChatMember {
    private String groupId;
    private ChatGroupMemberTypeEnum memberType;
    public ChatGroupMember(String memberId, String groupId,
                           WebSocketSession session, ChatMemberSessionStatus status, ChatGroupMemberTypeEnum type,
                           Stack<String> messageBox){
        this.memberId = memberId;
        this.groupId = groupId;
        this.session = session;
        this.status = status;
        this.memberType = type;
        this.messageBox = messageBox;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        ChatGroupMember member = (ChatGroupMember) obj;
        return Objects.equals(member.getMemberId(), this.memberId);
    }
}
