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

    final RestAccountService accountService;

    public AccountController(RestAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据电子邮箱检查是否用户是否存在
     * @param email 电子邮箱
     * @return true 存在
     */
    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return accountService.checkCustomer(email);
    }

    /**
     * 登录
     * @param name 名字
     * @param password 密码
     * @return 用户信息和token
     */
    @PostMapping("/login")
    public ResponseResult<Map<String,Object>> login(@RequestParam("nameOrEmail") @NotEmpty(message = LOGIN_FAIL_MSG)
                                                        @Length(max = 30,message = LOGIN_FAIL_MSG) String name,
                                     @RequestParam("password")
                                     @NotEmpty(message = LOGIN_FAIL_MSG)
                                     @Length(max = 20,message = LOGIN_FAIL_MSG) String password){
        return accountService.restLogin(name,password);
    }

    /**
     * 发送验证码
     * @param email 电子邮箱
     * @return 提示
     */
    @PostMapping("/sendCode")
    public ResponseResult<String> sendCode(@RequestParam("email")String email){
        return accountService.sendCode(email);
    }

    /**
     * 注册
     * @param code 验证码
     * @param customerDO 用户信息
     * @return 受影响的行数
     */
    @PostMapping("/register")
    public ResponseResult<Integer> register(@NotEmpty(message = "code cannot be empty")
                                                @Length(max = 5,min = 5,message = "Please fill in the correct verification code")
                                                @RequestParam("code")String code, @RequestBody CustomerDO customerDO){
        return accountService.restRegister(code,customerDO);
    }
}
