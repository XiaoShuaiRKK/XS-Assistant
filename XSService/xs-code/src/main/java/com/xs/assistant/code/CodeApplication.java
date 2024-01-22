package com.xs.assistant.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xs.assistant")
public class CodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class,args);
    }
}
