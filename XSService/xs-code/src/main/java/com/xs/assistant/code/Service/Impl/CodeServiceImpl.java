package com.xs.assistant.code.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.MailCode;
import com.xs.assistant.code.Service.CodeService;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.AbstractCodeUtil;
import com.xs.assistant.util.Impl.CommonUtil;
import com.xs.assistant.util.Impl.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CodeServiceImpl implements CodeService {

    private static final String CODE_KEY = "xs-assistant-code";

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    CommonUtil codeUtil;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    JsonUtil jsonUtil;

    @Override
    public ResponseResult<String> sendCode(String email) {
        String code = codeUtil.createCode(5);
        if(redisUtil.hasKey(CODE_KEY,email))
            return ResponseResult.success(null,"已发送,请稍后再试");
        redisUtil.setHash(CODE_KEY,email,code,120L);
        rabbitTemplate.convertAndSend("mailExchange","mail.code",
                jsonUtil.beanToJson(new MailCode(email,code)));
        return ResponseResult.success(null,"发送成功");
    }

    @Override
    public ResponseResult<String> sendRegisterSuccess(String email) {
        rabbitTemplate.convertAndSend("mailExchange","mail.register.success",email);
        return ResponseResult.success(null,"发送成功");
    }

    @Override
    public ResponseResult<Boolean> checkCode(String code, String email) {
        ResponseResult<Boolean> rs = null;
        try {
            String realCode = (String) redisUtil.getHash("xs-assistant-code",email);
            if(realCode != null && realCode.equals(code))
               rs = ResponseResult.success(Boolean.TRUE,"验证码正确");
            else
               rs = ResponseResult.success(Boolean.FALSE,"验证码错误");
        }catch (Exception e){
            log.error(e.getMessage());
            rs = ResponseResult.error(Boolean.FALSE,"服务器错误");
        }
        return rs;

    }
}
