package com.xs.assistant.account.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.RestAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    RestAccountService accountService;

    @PostMapping("/login")
    public ResponseResult<CustomerDO> login(@RequestParam("nameOrEmail")String name,
                                            @RequestParam("password")String password){
        return accountService.restLogin(name,password);
    }

}
