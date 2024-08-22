package com.xs.DAO.option.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatMessageTypeEnum {
    TEXT(1),
    FILE(2),
    GROUP(3);
    private final int code;
}
