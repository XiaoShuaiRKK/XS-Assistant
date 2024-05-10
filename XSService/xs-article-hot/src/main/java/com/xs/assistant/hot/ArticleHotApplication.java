package com.xs.assistant.hot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.xs.assistant")
@EnableTransactionManagement
public class ArticleHotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleHotApplication.class,args);
    }
}
