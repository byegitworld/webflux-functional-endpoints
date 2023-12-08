package com.example.demo.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.example.demo.entity.Person;

public interface PersonRepository extends R2dbcRepository<Person, Long> {
}
