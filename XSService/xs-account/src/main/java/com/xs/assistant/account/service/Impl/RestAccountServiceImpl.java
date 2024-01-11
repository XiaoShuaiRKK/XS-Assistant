package com.xs.assistant.account.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import com.xs.assistant.account.service.RestAccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestAccountServiceImpl implements RestAccountService {

    @Resource
    AccountService accountService;

    @Resource
    AccountInfoService accountInfoService;

    @Override
    @HystrixCommand(fallbackMethod = "accountTimeoutHandler")
    public ResponseResult<CustomerDO> restLogin(String name, String password) {
        ResponseResult<CustomerDO> result = null;
        int id;
        try {
            ResponseResult<Integer> loginResult = accountService.accountLogin(name,password);
            if((id = loginResult.getData()) < 0)
                result = ResponseResult.success(null,loginResult.getMessage());
            else
                result = accountInfoService.getCustomer(id);
        }catch (Exception e){
            result = ResponseResult.fail(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    public <T> ResponseResult<T> accountTimeoutHandler(){
        log.info("Level Down");
        return ResponseResult.unavailable("系统繁忙请稍后再试" + Thread.currentThread().getName());
    }
}
