package com.xs.assistant.account.service.fallback;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.remote.RemoteCodeService;
import org.springframework.stereotype.Component;

@Component
public class RemoteCodeFallback implements RemoteCodeService {
    @Override
    public ResponseResult<String> sendCode(String email) {
        return ResponseResult.unavailable("验证码系统正忙,请稍后重试");
    }

    @Override
    public ResponseResult<Boolean> checkCode(String code, String email) {
        return ResponseResult.unavailable("验证码系统正忙,请稍后重试");
    }
}
