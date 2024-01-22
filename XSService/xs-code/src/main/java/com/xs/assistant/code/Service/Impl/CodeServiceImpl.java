package com.xs.assistant.code.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.code.Service.CodeService;
import com.xs.assistant.util.AbstractCodeUtil;
import com.xs.assistant.util.Impl.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CodeServiceImpl implements CodeService {

    @Autowired(required = true)
    AbstractCodeUtil codeUtil;

    @Override
    public ResponseResult<Object> sendCode(String email) {
        String code = codeUtil.createCode(5);
        
    }
}
