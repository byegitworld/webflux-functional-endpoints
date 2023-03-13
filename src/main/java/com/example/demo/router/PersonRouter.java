package com.example.demo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.filter.IdBlockFilter;
import com.example.demo.handler.PersonHandler;

@Configuration
public class PersonRouter {
    @Bean
    public RouterFunction<ServerResponse> routePerson(PersonHandler personHandler) {
        return RouterFunctions.route()
            .GET("/ping", RequestPredicates.accept(MediaType.TEXT_PLAIN), personHandler::ping)
            .path("/person", b1 -> b1
                .nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), b2 ->b2
                    .GET("/{id}", personHandler::getPerson)
                    .GET(personHandler::listPeople)))
            .filter(new IdBlockFilter())
            .build();
    }
}
