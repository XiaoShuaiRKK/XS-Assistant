package com.xs.DAO.DO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mail {
    private String title;
    private String content;
    private String[] toEmail;
    private Map<String, Object> attachment;

    public void setToEmail(String... email){
        this.toEmail = email;
    }

}

