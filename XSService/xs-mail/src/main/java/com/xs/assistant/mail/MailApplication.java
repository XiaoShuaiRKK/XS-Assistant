package com.xs.assistant.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.xs.assistant",exclude = {DataSourceAutoConfiguration.class})
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class,args);
    }
}
