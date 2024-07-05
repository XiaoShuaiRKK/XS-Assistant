package com.xs.assistant.login.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-ENCRYPTION", path = "/xs_assistant")
public interface EncryptionRemoteService {
    @GetMapping("/encryption/encode")
    String getEncodePassword(@RequestParam("password")String password);

    @GetMapping("/encryption/check/encoded")
    Boolean checkEncodePassword(@RequestParam("password")String password,
                                @RequestParam("encodedPassword")String encodedPassword);
}
