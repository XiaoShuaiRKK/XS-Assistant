package com.xs.assistant.account.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import com.xs.assistant.account.service.RestAccountService;
import com.xs.assistant.account.service.remote.RemoteCodeService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RestAccountServiceImpl implements RestAccountService {

    @Resource
    AccountService accountService;
    @Resource
    AccountInfoService accountInfoService;
    @Autowired
    RemoteCodeService remoteCodeService;


    @Override
    //@Retry 将方法执行2次 如果2次都执行失败才会执行报错方法
    @Retry(name = "login-api", fallbackMethod = "systemFallback")
    public ResponseResult<CustomerDO> restLogin(String name, String password) {
        ResponseResult<CustomerDO> result = null;
        int id;
        ResponseResult<Integer> loginResult = accountService.accountLogin(name,password);
        if((id = loginResult.getData()) < 0)
            result = ResponseResult.success(null,loginResult.getMessage());
        else
            result = accountInfoService.getCustomer(id);
        return result;
    }

    @Override
    public ResponseResult<String> sendCode(String email) {
        return remoteCodeService.sendCode(email);
    }

    @Override
    public ResponseResult<Integer> restRegister(String code, CustomerDO customer) {
        ResponseResult<Boolean> rsCk = remoteCodeService.checkCode(code,customer.getEmail());
        if(!rsCk.getData())
            return ResponseResult.success(0,rsCk.getMessage());
        return accountInfoService.registerCustomer(customer);
    }

    @Override
    public ResponseResult<Boolean> checkCustomer(String email) {
        return accountInfoService.checkCustomer(email);
    }

    private <T> ResponseResult<T> systemFallback(Exception e){
        log.error(e.getMessage());
        return ResponseResult.unavailable("系统繁忙请稍后再试");
    }
}
