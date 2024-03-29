package com.xs.assistant.account.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.account.service.RestAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/account")
public class AccountController {

    private static final String LOGIN_FAIL_MSG = "账号或者密码错误";

    @Autowired
    RestAccountService accountService;

    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return accountService.checkCustomer(email);
    }

    @PostMapping("/login")
    public ResponseResult<Map<String,Object>> login(@RequestParam("nameOrEmail") @NotEmpty(message = LOGIN_FAIL_MSG)
                                                        @Length(max = 30,message = LOGIN_FAIL_MSG) String name,
                                     @RequestParam("password")
                                     @NotEmpty(message = LOGIN_FAIL_MSG)
                                     @Length(max = 20,message = LOGIN_FAIL_MSG) String password){
        return accountService.restLogin(name,password);
    }

    @PostMapping("/sendCode")
    public ResponseResult<String> sendCode(@RequestParam("email")String email){
        return accountService.sendCode(email);
    }

    @PostMapping("/register")
    public ResponseResult<Integer> register(@NotEmpty(message = "code cannot be empty")
                                                @Length(max = 5,min = 5,message = "Please fill in the correct verification code")
                                                @RequestParam("code")String code, @RequestBody CustomerDO customerDO){
        return accountService.restRegister(code,customerDO);
    }
}
