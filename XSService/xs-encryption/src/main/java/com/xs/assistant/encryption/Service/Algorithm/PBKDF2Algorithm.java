package com.xs.assistant.encryption.Service.Algorithm;

import com.xs.assistant.encryption.Config.SecurityConfig;
import com.xs.assistant.encryption.Service.EncryptionAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;


@RefreshScope
@Service
@Slf4j
public class PBKDF2Algorithm implements EncryptionAlgorithm {
    @Autowired
    @Qualifier("pbkdf2Encoder")
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;


    /**
     * 加密
     * @param pass password
     * @return 加密后的128位密码
     */
    @Override
    public String encryption(String pass) {
        return pbkdf2PasswordEncoder.encode(pass);
    }

    /**
     * 匹配
     * @param password 原密码
     * @param encodedPassword 加密后的密码
     * @return true 匹配 false 不匹配
     */
    @Override
    public Boolean encodedEquals(String password,String encodedPassword){
        return pbkdf2PasswordEncoder.matches(password,encodedPassword);
    }
}
