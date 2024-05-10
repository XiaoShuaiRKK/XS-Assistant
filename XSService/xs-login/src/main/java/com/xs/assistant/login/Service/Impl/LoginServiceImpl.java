package com.xs.assistant.login.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.login.DAO.LoginDAO;
import com.xs.assistant.login.Service.LoginService;
import com.xs.assistant.login.Service.Remote.EncryptionRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDAO loginDAO;
    @Autowired
    EncryptionRemoteService encryptionService;

    @Override
    public ResponseResult<CustomerDO> login(String name, String password) {
        ResponseResult<CustomerDO> result;
        try{

            String encodedPassword = loginDAO.getCustomerPassword(name);
            if(!encryptionService.checkEncodePassword(password,encodedPassword))
                return ResponseResult.success(null,"登录失败");
            result = ResponseResult.success(loginDAO.login(name),"登录成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result = ResponseResult.fail("server error");
        }
        return result;
    }
}
