package com.xs.assistant.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.xs.assistant",exclude = {DataSourceAutoConfiguration.class})
public class AddressApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class,args);
    }
}
