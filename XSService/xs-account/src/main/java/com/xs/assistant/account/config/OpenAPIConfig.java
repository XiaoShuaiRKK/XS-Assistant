package com.xs.assistant.account.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("接口标题")
                .description("SpringBoot3")
                .version("v1.2.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("/"));
    }
}
