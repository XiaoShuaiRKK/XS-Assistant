package com.xs.assistant.account.controller;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.RestAccountInfoService;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/query")
public class AccountSelectController {

    @Autowired
    AccountInfoService accountInfoService;

    @GetMapping("/customers")
    public ResponseResult<List<CustomerDO>> getAccounts(@Min(value = 1,message = "Please enter the correct value")
                                                            @RequestParam("page")Integer page,
                                                        @Min(value = 1,message = "Please enter the correct value")
                                                        @RequestParam("size")Integer size){
        return accountInfoService.getCustomers(page, size);
    }

    @GetMapping("/byNumberId")
    public ResponseResult<CustomerDO> getAccountById(@Length(min = 14,max = 14,message = "The Number ID format error")
                                                         @RequestParam("ID")String id){
        return accountInfoService.getCustomerByNumberId(id);
    }

    @GetMapping("/byEmail")
    public ResponseResult<CustomerDO> getAccountByEmail(@Email(message = "invalid email")
                                                            @RequestParam("email")String email){
        return accountInfoService.getCustomerByEmail(email);
    }
}
