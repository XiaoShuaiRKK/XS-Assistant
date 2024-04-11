package com.xs.assistant.service.user.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.service.user.DAO.UserInfoDAO;
import com.xs.assistant.service.user.Service.UserInfoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private static final String REDIS_CUSTOMER_KEY = "customer:";
    private static final String REDIS_CUSTOMER_EMAIL_KEY = "customerEmail:";
    private static final String REDIS_CUSTOMER_ID_NUMBER_KEY = "customerIdNumber:";
    private static final Long DEFAULT_KEY_TIME = 360L;

    @Autowired
    UserInfoDAO userInfoDAO;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取用户
     * @return list<account>
     */
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

    /**
     * 根据id查询用户
     * @param id id
     * @return account
     */
    @Override
    @Cacheable(cacheNames = "customer#10#s",key = "#id")
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
//    @RateLimiter(name = "user-flow-limit-api",fallbackMethod = "timeoutHandler")
    public ResponseResult<CustomerDO> getCustomer(Integer id) {
//        String number = String.valueOf(id);
//        if(redisUtil.hasKey(REDIS_CUSTOMER_KEY,number))
//            return ResponseResult.success((CustomerDO) redisUtil.getHash(REDIS_CUSTOMER_KEY,number));
        CustomerDO customer = userInfoDAO.selectCustomer(id);
//        redisUtil.setHash(REDIS_CUSTOMER_KEY,number,customer,DEFAULT_KEY_TIME);
        return ResponseResult.success(customer);
    }

    @Override
    @Cacheable(cacheNames = "customerByNumberID#10#s",key = "#numberID")
    @Transactional(rollbackFor = Exception.class)
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    public ResponseResult<CustomerDO> getCustomer(String numberID) {
        CustomerDO customer = userInfoDAO.selectCustomerByNumberId(numberID);
        return customer == null ? ResponseResult.success(null,"用户不存在")
                : ResponseResult.success(customer,"查询成功");
    }

    /**
     * 检查邮箱是否已被注册
     * @param email email
     * @return true 已被注册
     */
    @Override
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    public ResponseResult<Boolean> hasCustomer(String email) {
        if(redisUtil.hasKey(REDIS_CUSTOMER_EMAIL_KEY,email))
            return ResponseResult.success(true,"此邮箱已注册过");
        long id;
        boolean has = (id = userInfoDAO.selectCustomerByEmail(email)) > 0;
        if(has)
            redisUtil.setHash(REDIS_CUSTOMER_EMAIL_KEY,email,id,DEFAULT_KEY_TIME);
        String msg = has ? "此邮箱已注册过" : null;
        return ResponseResult.success(has,msg);
    }

    /**
     * 根据用户id 检查此用户是否存在
     * @param accountId ID Number
     * @return true 存在
     */
    @Override
    @CircuitBreaker(name = "user-breaker-api",fallbackMethod = "systemFailHandler")
    public ResponseResult<Boolean> hashCustomerByID(String accountId) {
        if(redisUtil.hasKey(REDIS_CUSTOMER_ID_NUMBER_KEY,accountId))
            return ResponseResult.success(true);
        long id;
        boolean has = (id = userInfoDAO.selectCustomerByID(accountId)) > 0;
        if(has)
            redisUtil.setHash(REDIS_CUSTOMER_ID_NUMBER_KEY,accountId, id,DEFAULT_KEY_TIME);
        String msg = has ? "用户存在" : "用户不存在";
        return ResponseResult.success(has,msg);
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
