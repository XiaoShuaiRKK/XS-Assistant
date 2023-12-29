package com.xs.assistant.login.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResponseResult<CustomerDO> login(String name,String password);
}
