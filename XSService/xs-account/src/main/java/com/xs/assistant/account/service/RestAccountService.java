package com.xs.assistant.account.service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface RestAccountService {
    ResponseResult<Map<String,Object>> restLogin(String name, String password);

    ResponseResult<String> sendCode(String email);

    ResponseResult<Boolean> restRegister(String code,CustomerDO customer);

    ResponseResult<Boolean> checkCustomer(String email);
    ResponseResult<Boolean> uploadIconWithCustomer(MultipartFile file,String idOfNumber);
}
