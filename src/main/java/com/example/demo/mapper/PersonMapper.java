package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;

@Component
public class PersonMapper {
    public Person mapToPerson(PersonDto personDto) {
        return Person.builder()
            .id(personDto.getPid())
            .name(personDto.getFullname())
            .build();
    }

    public PersonDto mapToPersonDto(Person person) {
        return PersonDto.builder()
            .pid(person.getId())
            .fullname(person.getName())
            .build();
    }
}
