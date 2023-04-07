package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.PersonDto;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public Mono<PersonDto> retrievePerson(Long id) {
        return personRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, id + " can't found"))))
            .map(personMapper::mapToPersonDto);
    }

    public Flux<PersonDto> retrievePeople() {
        return personRepository.findAll()
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "people can't found"))))
            .map(personMapper::mapToPersonDto);
    }
}
