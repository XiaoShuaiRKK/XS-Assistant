package com.xs.assistant.encryption.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
@Slf4j
public class SecurityConfig {
    /**
     * 随机盐长度
     */
    private static final int DEFAULT_SALT_LENGTH = 8;
    /**
     * 迭代次数
     */
    private static final int DEFAULT_ITERATIONS = 6;

    //@Value 从git仓库获取私钥
    @Bean(name = "pbkdf2Encoder")
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder(@Value("${algorithm.pbkdf2.password.secret}")String secret){
        log.info("Secret is " + secret);
        return new Pbkdf2PasswordEncoder(secret,DEFAULT_SALT_LENGTH,DEFAULT_ITERATIONS,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

    }


}
