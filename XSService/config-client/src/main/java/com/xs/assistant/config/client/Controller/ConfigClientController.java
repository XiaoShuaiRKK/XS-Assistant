package com.xs.assistant.config.client.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;
    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/getConfig")
    public String getConfig(){
        return """
                {
                    "info" : "%s",
                    "version" : "%s",
                    "port" : "%s"
                }
                """.formatted(configInfo,configVersion,serverPort);
    }
}
