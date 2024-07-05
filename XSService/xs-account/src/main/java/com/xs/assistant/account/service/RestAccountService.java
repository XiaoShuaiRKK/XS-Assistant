package com.xs.assistant.account.service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;

import java.util.Map;


public interface RestAccountService {
    ResponseResult<Map<String,Object>> restLogin(String name, String password);

    ResponseResult<String> sendCode(String email);

    ResponseResult<Boolean> restRegister(String code,CustomerDO customer);

    ResponseResult<Boolean> checkCustomer(String email);
}
