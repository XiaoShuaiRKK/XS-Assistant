package com.xs.assistant.encryption.Controller;

import com.xs.assistant.encryption.Service.EncryptionAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encryption")
public class EncryptionController {
    @Autowired
    EncryptionAlgorithm encryptionAlgorithm;

    @GetMapping("/encode")
    public String getEncodePassword(@RequestParam("password")String password){
        return encryptionAlgorithm.encryption(password);
    }

    @GetMapping("/check/encoded")
    public Boolean checkEncodePassword(@RequestParam("password")String password,
                                       @RequestParam("encodedPassword")String encodedPassword){
        return encryptionAlgorithm.encodedEquals(password,encodedPassword);
    }
}
