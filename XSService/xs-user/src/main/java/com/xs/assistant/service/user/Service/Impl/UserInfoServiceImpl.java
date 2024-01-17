package com.xs.assistant.service.user.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.service.user.DAO.UserInfoDAO;
import com.xs.assistant.service.user.Service.UserInfoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDAO userInfoDAO;

    @Override
    public ResponseResult<List<CustomerDO>> getCustomers() {
        ResponseResult<List<CustomerDO>> result = null;
        try{
            result = ResponseResult.success(userInfoDAO.getAllCustomer());
        }catch (Exception e){
            result = ResponseResult.fail(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
//    @RateLimiter(name = "user-flow-limit-api",fallbackMethod = "timeoutHandler")
    public ResponseResult<CustomerDO> getCustomer(Integer id) {
        ResponseResult<CustomerDO> result = null;
        result = ResponseResult.success(userInfoDAO.selectCustomer(id));
        return result;
    }


    private <T extends Serializable> ResponseResult<T> systemFailHandler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.error("系统错误,请联系管理员");
    }

    private  <T> ResponseResult<T> timeoutHandler(Integer id){
        log.info("Level Down");
        return ResponseResult.unavailable("系统繁忙请稍后再试" + Thread.currentThread().getName());
    }
}
