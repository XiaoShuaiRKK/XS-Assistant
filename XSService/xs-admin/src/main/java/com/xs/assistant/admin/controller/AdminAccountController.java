package com.xs.assistant.admin.controller;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.admin.service.AdminAccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/admin")
public class AdminAccountController {
    final AdminAccountService accountService;
    public AdminAccountController(AdminAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create/admin")
    public ResponseResult<Boolean> createAdmin(@RequestBody CustomerDO customer, @RequestParam("key") String key){
        return accountService.createAdmin(customer,key);
    }
}
