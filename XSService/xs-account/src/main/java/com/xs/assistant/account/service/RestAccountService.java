package com.xs.assistant.account.service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface RestAccountService {
    ResponseResult<Map<String,Object>> restLogin(String name, String password);

    ResponseResult<String> sendCode(String email);

    ResponseResult<Integer> restRegister(String code,CustomerDO customer);

    ResponseResult<Boolean> checkCustomer(String email);
}
