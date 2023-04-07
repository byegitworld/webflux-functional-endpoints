package com.example.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@RestController
public class Handler {

    public Mono<ServerResponse> ping(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
            .bodyValue(request.toString());
    }

}
