package com.xs.assistant.code.Service;

import com.xs.DAO.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public interface CodeService {
    ResponseResult<Object> sendCode(String email);
}
