package com.example.demo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.filter.IdBlockFilter;
import com.example.demo.handler.PersonHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
@Configuration
public class PersonRouter {
    @Bean
    public RouterFunction<ServerResponse> routePerson(PersonHandler personHandler) {
        System.out.println("routePerson");
        return RouterFunctions.route()
             .before(request -> {
                log.info("{}{} {}", request.exchange().getLogPrefix(), request.methodName(), request.uri());
                return request;
                // if(false) {
                //     return request;
                // } else {
                //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "!!!!!!!!!!!!!!");
                // }
             })
            .path("/person", b1 -> b1
                .nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), b2 ->b2
                    .GET("/{id}", personHandler::getPerson)
                    .filter(new IdBlockFilter())
                    .GET(personHandler::listPeople)))
            //  .before(request -> {
            //     log.info("{}{} {}", request.exchange().getLogPrefix(), request.methodName(), request.uri());
            //     return request;
            //  })
            //  .after((request, response) -> { // ServerException 발생 시, GlobalErrorWebExceptionHandler에서 응답 처리 되기 때문에 잘못된 http status 표시 됨
            //     log.info("{}{} {}", request.exchange().getLogPrefix(), response.statusCode(), response.headers().toString());
            //     return response;
            //  })
            .build();
    }
}
