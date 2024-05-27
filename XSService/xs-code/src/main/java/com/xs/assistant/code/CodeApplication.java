package com.xs.assistant.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.xs.assistant",exclude = {DataSourceAutoConfiguration.class})
public class CodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class,args);
    }
}
