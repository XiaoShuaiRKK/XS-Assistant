package com.xs.assistant.encryption.controller;

import com.xs.assistant.encryption.service.EncryptionAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encryption")
public class EncryptionController {
    final EncryptionAlgorithm encryptionAlgorithm;

    public EncryptionController(EncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    /**
     * 加密编码
     * @param password 原文
     * @return 加密值
     */
    @GetMapping("/encode")
    public String getEncodePassword(@RequestParam("password")String password){
        return encryptionAlgorithm.encryption(password);
    }

    /**
     * 比对编码
     * @param password 原文
     * @param encodedPassword 加密值
     * @return true 一致
     */
    @GetMapping("/check/encoded")
    public Boolean checkEncodePassword(@RequestParam("password")String password,
                                       @RequestParam("encodedPassword")String encodedPassword){
        return encryptionAlgorithm.encodedEquals(password,encodedPassword);
    }
}
