package com.xs.assistant.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.xs.assistant")
@EnableFeignClients
public class EncryptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncryptionApplication.class,args);
    }
}
