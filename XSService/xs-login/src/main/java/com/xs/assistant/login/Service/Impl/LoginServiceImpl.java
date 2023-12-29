package com.xs.assistant.login.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.login.DAO.LoginDAO;
import com.xs.assistant.login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDAO loginDAO;

    @Override
    public ResponseResult<CustomerDO> login(String name, String password) {
        return ResponseResult.success(loginDAO.selectCustomer(name,password));
    }
}
