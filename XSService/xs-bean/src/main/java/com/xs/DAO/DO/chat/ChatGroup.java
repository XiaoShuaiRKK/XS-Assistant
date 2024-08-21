package com.xs.DAO.DO.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class ChatGroup implements Serializable {
    private String groupId;
    private String groupName;
    private LocalDateTime createTime;
    private String leaderId;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        ChatGroup chatGroup = (ChatGroup) obj;
        return Objects.equals(chatGroup.groupId, this.groupId);
    }

    @Override
    public int hashCode(){
        return groupId.hashCode();
    }
}
