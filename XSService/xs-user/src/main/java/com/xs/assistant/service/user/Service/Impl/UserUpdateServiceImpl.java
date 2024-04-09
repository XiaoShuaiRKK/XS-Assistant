package com.xs.assistant.service.user.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.service.user.DAO.UserUpdateDAO;
import com.xs.assistant.service.user.Remote.EncryptionService;
import com.xs.assistant.service.user.Service.UserUpdateService;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Slf4j
public class UserUpdateServiceImpl implements UserUpdateService {

    private static final String REGISTER_COUNT_TODAY_KEY = "registerCount";

    @Autowired
    UserUpdateDAO userUpdateDAO;
    @Autowired
    EncryptionService encryptionService;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UIDCodeUtil uidCodeUtil;

    @Override
    @Retry(name = "user-customer-register-api",fallbackMethod = "systemFailHandler")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Integer> registerCustomer(CustomerDO customer) {
        int rs;
        long count;
        customer.setPassword(encryptionService.getEncodePassword(customer.getPassword()));
        count = redisUtil.increment(REGISTER_COUNT_TODAY_KEY);
        customer.setIdNumber(uidCodeUtil.createCode(count));
        String msg = (rs = userUpdateDAO.insertCustomer(customer)) <= 0 ? "注册失败" : "注册成功";
        return ResponseResult.success(rs,msg);
    }

    private <T extends Serializable> ResponseResult<T> systemFailHandler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.error("系统繁忙,请稍后重试");
    }
}
