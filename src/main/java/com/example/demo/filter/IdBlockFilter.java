package com.example.demo.filter;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class IdBlockFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private static final Set<String> blockList = Set.of(
        "2","4","299"
    );

    @Override
    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        try {
            String id = request.pathVariable("id");
            if (blockList.contains(id)) {
                return ServerResponse.status(HttpStatus.FORBIDDEN).build();
            } else {
                return next.handle(request);
            }
        } catch (IllegalArgumentException e) {
            return next.handle(request);
        }
    }
    
}
