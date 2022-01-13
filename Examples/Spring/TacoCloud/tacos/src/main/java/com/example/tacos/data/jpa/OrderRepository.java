package com.example.tacos.data.jpa;

import com.example.tacos.domain.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
}
