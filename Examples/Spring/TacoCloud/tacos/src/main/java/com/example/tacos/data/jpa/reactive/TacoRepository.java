package com.example.tacos.data.jpa.reactive;

import com.example.tacos.domain.reactive.mongo.Taco;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository("tacoRepo")
public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {
    
    Flux<Taco> findByOrderByCreatedAt();

}
