package com.xs.assistant.account.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseStatus;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.util.Impl.JWTUtil;
import com.xs.assistant.account.service.remote.AccountInfoService;
import com.xs.assistant.account.service.remote.AccountService;
import com.xs.assistant.account.service.RestAccountService;
import com.xs.assistant.account.service.remote.RemoteCodeService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.internal.Cache;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RestAccountServiceImpl implements RestAccountService {

    final RemoteCodeService remoteCodeService;
    @Resource
    AccountService accountService;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    RedisUtil redisUtil;

    public RestAccountServiceImpl(RemoteCodeService remoteCodeService) {
        this.remoteCodeService = remoteCodeService;
    }

    /**
     * 登录
     * @param name name
     * @param password password
     * @return 用户信息和token
     */
    @Override
    //@Retry 将方法执行2次 如果2次都执行失败才会执行报错方法
    @Retry(name = "login-api", fallbackMethod = "systemFallback")
    public ResponseResult<Map<String,Object>> restLogin(String name, String password) {
        String token;
        Map<String,Object> result = new HashMap<>();
        ResponseResult<CustomerDO> customerPack = accountService.accountLogin(name,password);
        if(!customerPack.getStatus().equals(ResponseStatus.HTTP_STATUS_200.getResponseCode())){
            return ResponseResult.error(null,customerPack.getMessage());
        }
        CustomerDO customer = customerPack.getData();
        //检查用户是否已经的登录过且有未过期的token
        //如果存在则直接返回此token和用户信息
        Optional<Object> tokenOptional = checkAccountHasToken(customer.getIdNumber());
        if(tokenOptional.isPresent())
            return loginSuccess(result,customer,tokenOptional.get().toString());
        //根据用户id和名字来生成唯一token
        Map<String,String> payload = new HashMap<>();
        payload.put(JWTUtil.JWTKey.ID_NUMBER_KEY,customer.getIdNumber());
        payload.put(JWTUtil.JWTKey.NAME_KEY,customer.getFirstName() + customer.getLastName());
        //生成token
        token = JWTUtil.getToken(payload, Calendar.DATE,JWTUtil.TIME_OUT_DAY);
        //存储到redis中
        redisUtil.setHash(JWTUtil.JWTKey.REDIS_KEY,customer.getIdNumber(),token,
                Long.valueOf(JWTUtil.TIME_OUT_DAY), TimeUnit.DAYS);
        return loginSuccess(result,customer,token);
    }

    /**
     * 发送验证码
     * @param email 电子邮箱
     * @return 提示
     */
    @Override
    public ResponseResult<String> sendCode(String email) {
        //检查是否存在用户 若存在则不会发送
        ResponseResult<Boolean> hasCustomer = accountInfoService.checkCustomer(email);
        if(Boolean.TRUE.equals(hasCustomer.getData()))
            return ResponseResult.success(null,hasCustomer.getMessage());
        return remoteCodeService.sendCode(email);
    }

    /**
     * 注册
     * @param code 验证码
     * @param customer 用户信息
     * @return 受影响行数
     */
    @Override
    public ResponseResult<Boolean> restRegister(String code, CustomerDO customer) {
        ResponseResult<Boolean> rsCk = remoteCodeService.checkCode(code,customer.getEmail());
        if(Boolean.FALSE.equals(rsCk.getData()))
            return ResponseResult.success(false,rsCk.getMessage());
        return accountInfoService.registerCustomer(customer);
    }

    /**
     * 根据电子邮箱检查是否存在此用户
     * @param email 电子邮箱
     * @return true存在
     */
    @Override
    public ResponseResult<Boolean> checkCustomer(String email) {
        return accountInfoService.checkCustomer(email);
    }

    @Override
    public ResponseResult<Boolean> uploadIconWithCustomer(MultipartFile file, String idOfNumber) {
        return accountInfoService.uploadIconWithCustomer(file,idOfNumber);
    }

    /**
     * 查询用户是否已经存在未过期的token
     * @param number id
     * @return token
     */
    private Optional<Object> checkAccountHasToken(String number){
        return Optional.ofNullable(redisUtil.getHash(JWTUtil.JWTKey.REDIS_KEY,number));
    }

    /**
     * 封装
     * @param result 结果
     * @param customer 用户
     * @param token token
     * @return 用户信息和token
     * @param <T> 结果
     */
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
