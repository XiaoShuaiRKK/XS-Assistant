package com.xs.assistant.code.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.MailCode;
import com.xs.assistant.code.service.CodeService;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.util.Impl.CommonUtil;
import com.xs.assistant.util.Impl.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CodeServiceImpl implements CodeService {

    private static final String CODE_KEY = "xs-assistant-code";

    final RabbitTemplate rabbitTemplate;
    final CommonUtil codeUtil;
    final RedisUtil redisUtil;
    final JsonUtil jsonUtil;

    public CodeServiceImpl(RabbitTemplate rabbitTemplate, CommonUtil codeUtil, RedisUtil redisUtil, JsonUtil jsonUtil) {
        this.rabbitTemplate = rabbitTemplate;
        this.codeUtil = codeUtil;
        this.redisUtil = redisUtil;
        this.jsonUtil = jsonUtil;
    }

    /**
     * 发送验证码
     * @param email 电子邮箱
     * @return 提示信息
     */
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

    /**
     * 发送注册成功信息到指定邮箱
     * @param email 电子邮箱
     * @return 提示信息
     */
    @Override
    public ResponseResult<String> sendRegisterSuccess(String email) {
        rabbitTemplate.convertAndSend("mailExchange","mail.register.success",email);
        return ResponseResult.success(null,"发送成功");
    }

    /**
     * 验证验证码
     * @param code 验证码
     * @param email 电子邮箱
     * @return 是否正确
     */
    @Override
    public ResponseResult<Boolean> checkCode(String code, String email) {
        ResponseResult<Boolean> rs = null;
        try {
            String realCode = (String) redisUtil.getHash(CODE_KEY,email);
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
