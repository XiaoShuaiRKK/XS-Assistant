package com.xs.assistant.service.user.Remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "XS-ENCRYPTION",path = "/xs_assistant/encryption")
public interface EncryptionService {
    @GetMapping("/encode")
    String getEncodePassword(@RequestParam("password")String password);

    @GetMapping("/check/encoded")
    Boolean checkEncodePassword(@RequestParam("password")String password,
                                       @RequestParam("encodedPassword")String encodedPassword);
}
