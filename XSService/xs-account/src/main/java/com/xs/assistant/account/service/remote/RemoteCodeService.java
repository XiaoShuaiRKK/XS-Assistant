package com.xs.assistant.account.service.remote;

import com.xs.DAO.ResponseResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "XS-CODE",path = "/xs_assistant")
public interface RemoteCodeService {
    @PostMapping("/code/send")
    ResponseResult<String> sendCode(@Valid @Email(message = "invalid email")
                                           @RequestParam("email")String email);

    @PostMapping("/code/check")
    ResponseResult<Boolean> checkCode(@RequestParam("code")String code,@RequestParam("email")String email);
}
