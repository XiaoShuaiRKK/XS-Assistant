package com.xs.assistant.login.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/customer")
    public ResponseResult<CustomerDO> login(@RequestParam("nameOrEmail")String name,
                                            @RequestParam("password")String password){
        return loginService.login(name,password);
    }

    @GetMapping("/test")
    public ResponseResult<String> test(){
        return ResponseResult.success("test success");
    }
}
