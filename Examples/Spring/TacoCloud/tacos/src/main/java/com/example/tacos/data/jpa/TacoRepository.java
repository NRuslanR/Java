package com.example.tacos.data.jpa;

import com.example.tacos.domain.Taco;

import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    
}
