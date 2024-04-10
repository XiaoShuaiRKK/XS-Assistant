package com.xs.assistant.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.xs.assistant")
@EnableFeignClients
public class LoginMain {
    public static void main(String[] args) {
        SpringApplication.run(LoginMain.class,args);
    }
}
