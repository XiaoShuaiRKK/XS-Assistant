package com.xs.DAO.DO.customer;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class MailCode implements Serializable {
    @Email(message = "invalid email")
    private String email;
    private String code;
}
