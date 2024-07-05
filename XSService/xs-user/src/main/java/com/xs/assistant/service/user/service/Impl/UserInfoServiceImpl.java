package com.xs.assistant.service.user.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.aspect.Annotation.RedisSetHash;
import com.xs.assistant.redis.aspect.Annotation.RedisSetHashValues;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.service.user.DAO.UserInfoDAO;
import com.xs.assistant.service.user.DAO.UserMapper;
import com.xs.assistant.service.user.service.UserInfoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private static final String REDIS_CUSTOMER_KEY = "customer:";
    private static final String REDIS_CUSTOMER_EMAIL_KEY = "customerEmail:";
    private static final String REDIS_CUSTOMER_ID_NUMBER_KEY = "customerIdNumber:";
    private static final String REDIS_CUSTOMER_ID_KEY = "customerId:";
    private static final Long DEFAULT_KEY_TIME_S = 360L;
    private static final int DEFAULT_KEY_TIME_M = 5;

    final UserInfoDAO userInfoDAO;
    final UserMapper userMapper;
    final RedisUtil redisUtil;

    public UserInfoServiceImpl(UserInfoDAO userInfoDAO, UserMapper userMapper, RedisUtil redisUtil) {
        this.userInfoDAO = userInfoDAO;
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }

    @Qualifier("userInfoService")

    /**
     * 获取用户
     * @return list<account>
     */
    @Override
    public List<CustomerDO> getCustomers() {
        try{
            return userInfoDAO.getAllCustomer();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public IPage<CustomerDO> getCustomers(Integer page, Integer size) {
        return userMapper.selectPage(Page.of(page,size));
    }

    /**
     * 根据id查询用户
     * @param id id
     * @return account
     */
    @Override
    @RedisSetHashValues(baseKey = REDIS_CUSTOMER_KEY,keyName = REDIS_CUSTOMER_ID_KEY,
            key = "#id",time = DEFAULT_KEY_TIME_M,timeStyle = TimeUnit.MINUTES)
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    public CustomerDO getCustomer(Integer id) {
        return userInfoDAO.selectCustomer(id);
    }

    @Override
    @RedisSetHashValues(baseKey = REDIS_CUSTOMER_KEY,keyName = REDIS_CUSTOMER_ID_NUMBER_KEY,
            key = "#numberID",time = DEFAULT_KEY_TIME_M,timeStyle = TimeUnit.MINUTES)
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    public CustomerDO getCustomer(String numberID) {
        return userInfoDAO.selectCustomerByNumberId(numberID);
    }

    @Override
    @RedisSetHashValues(baseKey = REDIS_CUSTOMER_KEY,keyName = REDIS_CUSTOMER_EMAIL_KEY,
            key = "#email",time = DEFAULT_KEY_TIME_M,timeStyle = TimeUnit.MINUTES)
    public CustomerDO getCustomerByEmail(String email) {
        return userInfoDAO.selectCustomerInfoByEmail(email);
    }

    /**
     * 检查邮箱是否已被注册
     * @param email email
     * @return true 已被注册
     */
    @Override
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    @RedisSetHash(keyName = REDIS_CUSTOMER_EMAIL_KEY,key = "#email",time = 3,timeStyle = TimeUnit.MINUTES)
    public Boolean hasCustomer(String email) {
        return userInfoDAO.selectCustomerByEmail(email) != null;
    }

    /**
     * 根据用户id 检查此用户是否存在
     * @param accountId ID Number
     * @return true 存在
     */
    @Override
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    @RedisSetHash(keyName = REDIS_CUSTOMER_ID_NUMBER_KEY,key = "#accountId",time = 3,timeStyle = TimeUnit.MINUTES)
    public Boolean hashCustomerByID(String accountId) {
        return userInfoDAO.selectCustomerByID(accountId) > 0;
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
