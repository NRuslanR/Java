package com.example.tacos.data.jpa.reactive;

import com.example.tacos.domain.Customer;
import com.example.tacos.domain.reactive.mongo.Order;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository("orderRepo")
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    
    Flux<Order> findByCustomerOrderByPlacedAtDesc(Customer customer);
    
}
