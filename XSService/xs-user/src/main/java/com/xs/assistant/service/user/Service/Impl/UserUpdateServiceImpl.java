package com.xs.assistant.service.user.Service.Impl;

import com.netflix.discovery.converters.Auto;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.service.user.DAO.UserUpdateDAO;
import com.xs.assistant.service.user.Service.UserUpdateService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Slf4j
public class UserUpdateServiceImpl implements UserUpdateService {

    @Autowired
    UserUpdateDAO userUpdateDAO;

    @Override
    @Retry(name = "user-customer-register-api",fallbackMethod = "systemFailHandler")
    public ResponseResult<Integer> registerCustomer(CustomerDO customer) {
        int rs;
        String msg = (rs = userUpdateDAO.insertCustomer(customer)) <= 0 ? "注册失败" : "注册成功";
        return ResponseResult.success(rs,msg);
    }

    private <T extends Serializable> ResponseResult<T> systemFailHandler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.error("系统错误,请联系管理员");
    }
}
