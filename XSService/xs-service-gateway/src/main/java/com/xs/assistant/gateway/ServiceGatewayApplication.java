package com.xs.assistant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.xs.assistant",exclude = {DataSourceAutoConfiguration.class})
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class,args);
    }
}
