package com.xs.assistant.code.service;

import com.xs.DAO.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public interface CodeService {
    ResponseResult<String> sendCode(String email);

    ResponseResult<String> sendRegisterSuccess(String email);

    ResponseResult<Boolean> checkCode(String code,String email);
}
