package com.example.demo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.handler.Handler;

@Order(1)
@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> route(Handler handler) {
        System.out.println("route");
        return RouterFunctions.route()
            .GET("/ping", RequestPredicates.accept(MediaType.TEXT_PLAIN), handler::ping)
            .build();
    }
}
