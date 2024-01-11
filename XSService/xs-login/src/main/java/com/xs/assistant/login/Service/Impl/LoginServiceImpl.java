package com.xs.assistant.login.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
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
    public ResponseResult<Integer> login(String name, String password) {
        ResponseResult<Integer> result;
        try{
            int id = loginDAO.login(name,password);
            String msg = id > 0 ? "登录成功" : "账号或者密码错误";
            result = ResponseResult.success(id,msg);
        }catch (Exception e){
            log.info(e.getMessage());
            result = ResponseResult.fail("server error");
        }
        return result;
    }
}
