package com.xs.assistant.chat.config;

import com.xs.assistant.chat.handler.GroupAuthHandler;
import com.xs.assistant.chat.handler.HttpAuthHandler;
import com.xs.assistant.chat.interceptor.GroupInterceptor;
import com.xs.assistant.chat.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private HttpAuthHandler httpAuthHandler;
    @Autowired
    private MyInterceptor myInterceptor;
    @Autowired
    private GroupAuthHandler groupAuthHandler;
    @Autowired
    private GroupInterceptor groupInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(httpAuthHandler,"public")
                .addInterceptors(myInterceptor)
                .setAllowedOrigins("*");
        registry.addHandler(groupAuthHandler,"group")
                .addInterceptors(groupInterceptor)
                .setAllowedOrigins("*");
    }
}
