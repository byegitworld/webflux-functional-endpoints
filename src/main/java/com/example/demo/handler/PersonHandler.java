package com.example.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.dto.PersonDto;
import com.example.demo.service.PersonService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PersonHandler {
    private final PersonService personService;

    public Mono<ServerResponse> ping(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
            .bodyValue(request.toString());
    }
    
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        Mono<PersonDto> personDtoMono = personService.retrievePerson(Long.valueOf(request.pathVariable("id")));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personDtoMono, PersonDto.class);
    }

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Flux<PersonDto> personDtoFlux = personService.retrievePeople();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personDtoFlux, PersonDto.class);
    }
}
