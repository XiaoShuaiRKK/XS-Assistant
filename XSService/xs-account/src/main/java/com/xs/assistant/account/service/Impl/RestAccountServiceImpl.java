package com.xs.assistant.account.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import com.xs.assistant.account.service.RestAccountService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RestAccountServiceImpl implements RestAccountService {

    @Resource
    AccountService accountService;

    @Resource
    AccountInfoService accountInfoService;

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

    private <T> ResponseResult<T> systemFallback(Exception e){
        log.error(e.getMessage());
        return ResponseResult.unavailable("系统繁忙请稍后再试");
    }
}
