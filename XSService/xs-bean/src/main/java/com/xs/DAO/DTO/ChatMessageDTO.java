package com.xs.DAO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO implements Serializable {
    private String message;
    private String toId;
    private String senderId;
    private LocalDateTime time;
}
