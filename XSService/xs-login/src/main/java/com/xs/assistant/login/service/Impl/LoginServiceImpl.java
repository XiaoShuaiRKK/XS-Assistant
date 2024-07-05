package com.xs.assistant.login.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.login.DAO.LoginDAO;
import com.xs.assistant.login.service.LoginService;
import com.xs.assistant.login.service.remote.EncryptionRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    final LoginDAO loginDAO;
    final EncryptionRemoteService encryptionService;

    public LoginServiceImpl(LoginDAO loginDAO, EncryptionRemoteService encryptionService) {
        this.loginDAO = loginDAO;
        this.encryptionService = encryptionService;
    }

    /**
     * 登录
     * @param name 用户名
     * @param password 密码
     * @return 用户
     */
    @SuppressWarnings("all")
    @Override
    public ResponseResult<CustomerDO> login(String name, String password) {
        ResponseResult<CustomerDO> result;
        try{
            //查询用户对应加密的密码
            Optional<String> encodedPassword = Optional.ofNullable(loginDAO.getCustomerPassword(name));
            //比对原文和加密值是否一致
            if (encodedPassword.isPresent()){
                if(Boolean.TRUE.equals(encryptionService.checkEncodePassword(password,encodedPassword.get())))
                    return ResponseResult.success(loginDAO.login(name),"登录成功");
            }
            result = ResponseResult.success(null,"登录失败");
        }catch (Exception e){
            log.error(e.getMessage());
            result = ResponseResult.fail("server error");
        }
        return result;
    }
}
