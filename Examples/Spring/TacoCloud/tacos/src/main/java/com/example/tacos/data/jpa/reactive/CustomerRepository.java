package com.example.tacos.data.jpa.reactive;

import com.example.tacos.domain.reactive.mongo.Customer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepo")
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    
}
