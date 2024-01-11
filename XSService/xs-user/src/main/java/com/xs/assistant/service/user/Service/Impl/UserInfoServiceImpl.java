package com.xs.assistant.service.user.Service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.service.user.DAO.UserInfoDAO;
import com.xs.assistant.service.user.Service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDAO userInfoDAO;

    @HystrixCommand(fallbackMethod = "timeoutHandler",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true")
    })
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
    public ResponseResult<CustomerDO> getCustomer(Integer id) {
        ResponseResult<CustomerDO> result = null;
        try{
            result = ResponseResult.success(userInfoDAO.selectCustomer(id));
        }catch (Exception e){
            result = ResponseResult.fail(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    public  <T> ResponseResult<T> timeoutHandler(){
        log.info("Level Down");
        return ResponseResult.unavailable("系统繁忙请稍后再试" + Thread.currentThread().getName());
    }
}
