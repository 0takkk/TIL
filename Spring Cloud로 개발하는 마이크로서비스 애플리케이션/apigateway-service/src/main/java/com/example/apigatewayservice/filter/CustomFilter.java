package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter(){
        super(Config.class);
    }

    public static class Config{
        // configuration 정보가 있다면 내부 클래스를 이용해서 넣을 수 있다.
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter
        return (exchange, chain) -> {
            // netty라는 비동기 방식의 서버 사용
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE Filter : request id -> {}", request.getId());

            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 이전에 썼던 ServletRequest, Response가 아니라 스프링 5에서 지원되는 Web Flux 를 이용해서
                // 서버를 구축할 때 반환값으로 Mono데이터 타입을 사용할 수 있다. (데이터타입을 하나 주겠다는 의미)
                // Mono : 0 ~ 1개 데이터 전달, Flux : 0 ~ N개 데이터 전달
                log.info("Custom Post Filter : response code -> {}", response.getStatusCode());
            }));
        };
    }
}
