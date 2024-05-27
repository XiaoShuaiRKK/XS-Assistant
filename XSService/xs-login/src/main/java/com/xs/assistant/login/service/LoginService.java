package com.xs.assistant.login.service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResponseResult<CustomerDO> login(String name,String password);

}
