package com.xs.assistant.system.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.xs.assistant")
@EnableCaching
@EnableFeignClients
public class SystemInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemInfoApplication.class, args);
    }
}
