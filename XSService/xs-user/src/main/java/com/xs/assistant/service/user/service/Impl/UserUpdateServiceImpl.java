package com.xs.assistant.service.user.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.option.AccountLevelEnum;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.service.user.DAO.UserMapper;
import com.xs.assistant.service.user.DAO.UserUpdateDAO;
import com.xs.assistant.service.user.remote.EncryptionService;
import com.xs.assistant.service.user.service.UserUpdateService;
import com.xs.assistant.util.function.RedisKeyFunction;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Slf4j
public class UserUpdateServiceImpl implements UserUpdateService {

    private static final String REGISTER_COUNT_TODAY_KEY = "registerCount";

    final UserUpdateDAO userUpdateDAO;
    final UserMapper userMapper;
    final EncryptionService encryptionService;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UIDCodeUtil uidCodeUtil;

    private final RedisKeyFunction<String,Long> registerHas = (key,value) -> redisUtil.increment(key,1L);

    public UserUpdateServiceImpl(UserUpdateDAO userUpdateDAO, UserMapper userMapper, EncryptionService encryptionService) {
        this.userUpdateDAO = userUpdateDAO;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    @Override
//    @Retry(name = "user-customer-register-api",fallbackMethod = "systemFailHandler")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> registerCustomer(CustomerDO customer) {
        boolean rs = registerAccount(customer, AccountLevelEnum.ACCOUNT.ordinal());
        return ResponseResult.success(rs,rs ? "注册失败" : "注册成功");
    }

    @Override
    public ResponseResult<Boolean> registerAdmin(CustomerDO customer) {
        boolean rs = registerAccount(customer, AccountLevelEnum.ADMIN.ordinal());
        return ResponseResult.success(rs,rs ? "注册失败" : "注册成功");
    }

    private boolean registerAccount(CustomerDO customer,Integer level){
        long count;
        customer.setLevel(level);
        customer.setPassword(encryptionService.getEncodePassword(customer.getPassword()));
        registerHas.hasKey(redisUtil.hasKey(REGISTER_COUNT_TODAY_KEY),((k,u) -> redisUtil.increment(k,u)))
                .execute(REGISTER_COUNT_TODAY_KEY, userMapper.selectAllCount());
        count = redisUtil.increment(REGISTER_COUNT_TODAY_KEY);
        customer.setIdNumber(uidCodeUtil.createCode(count));
        return userUpdateDAO.insertCustomer(customer) <= 0;
    }

    private <T extends Serializable> ResponseResult<T> systemFailHandler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.error("系统繁忙,请稍后重试");
    }
}
