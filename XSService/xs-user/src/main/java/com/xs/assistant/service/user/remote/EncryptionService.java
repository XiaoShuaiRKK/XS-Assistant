package com.xs.assistant.service.user.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-ENCRYPTION",path = "/xs_assistant/encryption")
@Service
public interface EncryptionService {
    @GetMapping("/encode")
    String getEncodePassword(@RequestParam("password")String password);

    @GetMapping("/check/encoded")
    Boolean checkEncodePassword(@RequestParam("password")String password,
                                       @RequestParam("encodedPassword")String encodedPassword);
}
