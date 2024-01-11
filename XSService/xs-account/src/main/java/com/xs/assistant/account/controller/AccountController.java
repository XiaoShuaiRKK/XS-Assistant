package com.xs.assistant.account.controller;

import com.netflix.discovery.converters.Auto;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.RestAccountService;
import com.xs.assistant.account.service.remote.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    RestAccountService accountService;

    @PostMapping("login")
    public ResponseResult<CustomerDO> login(@RequestParam("nameOrEmail")String name,
                                            @RequestParam("password")String password){
        return accountService.restLogin(name,password);
    }
}
