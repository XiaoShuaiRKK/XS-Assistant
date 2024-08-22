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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMember {
    protected String memberId;
    private String toId;
    protected WebSocketSession session;
    protected ChatMemberSessionStatus status;
    protected Stack<String> messageBox;
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        ChatMember member = (ChatMember) obj;
        return Objects.equals(member.getMemberId(), this.memberId);
    }
}