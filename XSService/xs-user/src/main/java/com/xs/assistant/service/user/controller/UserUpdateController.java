package com.xs.assistant.service.user.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.service.user.service.UserUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserUpdateController {

    final UserUpdateService userUpdateService;

    public UserUpdateController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping("/register")
    public ResponseResult<Integer> registerCustomer(@RequestBody CustomerDO customer){
        return userUpdateService.registerCustomer(customer);
    }

}
