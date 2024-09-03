package com.xs.assistant.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.xs.assistant",exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class,args);
    }
}
