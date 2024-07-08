package com.xs.DAO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {
    private String data;
    private String title;
    private String content;
}
