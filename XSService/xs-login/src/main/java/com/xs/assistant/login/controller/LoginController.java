package com.xs.assistant.login.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录
     * @param name 用户名
     * @param password 密码
     * @return 用户
     */
    @PostMapping("/customer")
    public ResponseResult<CustomerDO> login(@RequestParam("nameOrEmail")String name,
                                            @RequestParam("password")String password){
        return loginService.login(name,password);
    }
}
