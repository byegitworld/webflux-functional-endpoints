package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonDto;
import com.example.demo.error.ServerException;
import com.example.demo.error.Error;
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
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ServerException(Error.DATA_NOT_FOUND))))
            .map(personMapper::mapToPersonDto);
    }

    public Flux<PersonDto> retrievePeople() {
        return personRepository.findAll()
            .map(personMapper::mapToPersonDto)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ServerException(Error.DATA_NOT_FOUND))));
    }
}
