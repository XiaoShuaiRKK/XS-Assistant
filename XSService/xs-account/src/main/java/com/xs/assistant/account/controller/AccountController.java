package com.xs.assistant.account.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.RestAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    RestAccountService accountService;

    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return accountService.checkCustomer(email);
    }

    @PostMapping("/login")
    public ResponseResult<Map<String,Object>> login(@RequestParam("nameOrEmail")String name,
                                     @RequestParam("password")String password){
        return accountService.restLogin(name,password);
    }

    @PostMapping("/sendCode")
    public ResponseResult<String> sendCode(@RequestParam("email")String email){
        return accountService.sendCode(email);
    }

    @PostMapping("/register")
    public ResponseResult<Integer> register(@RequestParam("code")String code,
                                            @RequestBody CustomerDO customerDO){
        return accountService.restRegister(code,customerDO);
    }
}
