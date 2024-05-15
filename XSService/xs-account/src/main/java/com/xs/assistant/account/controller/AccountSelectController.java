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

    final AccountInfoService accountInfoService;

    public AccountSelectController(AccountInfoService accountInfoService) {
        this.accountInfoService = accountInfoService;
    }

    /**
     * 获得用户列表
     * @param page 页数
     * @param size 大小
     * @return 用户列表
     */
    @GetMapping("/customers")
    public ResponseResult<List<CustomerDO>> getAccounts(@Min(value = 1,message = "Please enter the correct value")
                                                            @RequestParam("page")Integer page,
                                                        @Min(value = 1,message = "Please enter the correct value")
                                                        @RequestParam("size")Integer size){
        return accountInfoService.getCustomers(page, size);
    }

    /**
     * 根据id获取用户
     * @param id id
     * @return 用户
     */
    @GetMapping("/byNumberId")
    public ResponseResult<CustomerDO> getAccountById(@Length(min = 14,max = 14,message = "The Number ID format error")
                                                         @RequestParam("ID")String id){
        return accountInfoService.getCustomerByNumberId(id);
    }

    /**
     * 根据电子邮箱获取用户
     * @param email 电子邮箱
     * @return 用户
     */
    @GetMapping("/byEmail")
    public ResponseResult<CustomerDO> getAccountByEmail(@Email(message = "invalid email")
                                                            @RequestParam("email")String email){
        return accountInfoService.getCustomerByEmail(email);
    }
}
