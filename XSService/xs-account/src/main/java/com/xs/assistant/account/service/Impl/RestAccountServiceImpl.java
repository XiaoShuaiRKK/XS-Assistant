package com.xs.assistant.account.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.Impl.JWTUtil;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import com.xs.assistant.account.service.RestAccountService;
import com.xs.assistant.account.service.remote.RemoteCodeService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
    @Resource
    RedisUtil redisUtil;

    @Override
    //@Retry 将方法执行2次 如果2次都执行失败才会执行报错方法
    @Retry(name = "login-api", fallbackMethod = "systemFallback")
    public ResponseResult<Map<String,Object>> restLogin(String name, String password) {
        String token;
        Map<String,Object> result = new HashMap<>();
        ResponseResult<CustomerDO> customerPack = accountService.accountLogin(name,password);
        CustomerDO customer = customerPack.getData();
        if(customer == null)
            return ResponseResult.success(null,customerPack.getMessage());
        if((token = checkAccountHasToken(customer.getIdNumber())) != null)
            return loginSuccess(result,customer,token);
        Map<String,String> payload = new HashMap<>();
        payload.put(JWTUtil.JWTKey.ID_NUMBER_KEY,customer.getIdNumber());
        payload.put(JWTUtil.JWTKey.NAME_KEY,customer.getFirstName() + customer.getLastName());
        token = JWTUtil.getToken(payload);
        redisUtil.setHash(JWTUtil.JWTKey.REDIS_KEY,customer.getIdNumber(),token,
                Long.valueOf(JWTUtil.TIME_OUT_DAY), TimeUnit.DAYS);
        return loginSuccess(result,customer,token);
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

    private String checkAccountHasToken(String number){
        Object tokenObject = redisUtil.getHash(JWTUtil.JWTKey.REDIS_KEY,number);
        return tokenObject != null ? (String) tokenObject : null;
    }

    private <T extends Map<String,Object>> ResponseResult<T> loginSuccess(T result,CustomerDO customer,String token){
        result.put("customer",customer);
        result.put("token",token);
        return ResponseResult.success(result,"登录成功");
    }

    private <T> ResponseResult<T> systemFallback(Exception e){
        log.error(e.getMessage());
        return ResponseResult.unavailable("系统繁忙请稍后再试");
    }
}
