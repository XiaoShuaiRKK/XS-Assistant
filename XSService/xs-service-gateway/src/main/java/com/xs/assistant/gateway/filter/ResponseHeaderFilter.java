package com.xs.assistant.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ResponseHeaderFilter extends BaseFilter implements GlobalFilter, Ordered {
    @Value("${assistant.project.version}")
    private String projectVersion;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .then(Mono.fromRunnable(
                        () -> exchange.getResponse().getHeaders().add("XS-Assistant-Project-Version",projectVersion)
                ));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
