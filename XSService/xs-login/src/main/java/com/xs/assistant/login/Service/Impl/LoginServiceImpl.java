package com.xs.assistant.login.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.login.DAO.LoginDAO;
import com.xs.assistant.login.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDAO loginDAO;

    @Override
    public ResponseResult<CustomerDO> login(String name, String password) {
        ResponseResult<CustomerDO> result;
        try{
            CustomerDO customer = loginDAO.login(name,password);
            String msg = customer != null ? "登录成功" : "账号或者密码错误";
            result = ResponseResult.success(customer,msg);
        }catch (Exception e){
            log.info(e.getMessage());
            result = ResponseResult.fail("server error");
        }
        return result;
    }
}
