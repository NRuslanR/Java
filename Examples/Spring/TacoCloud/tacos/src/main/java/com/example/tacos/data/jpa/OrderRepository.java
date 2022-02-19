package com.example.tacos.data.jpa;

import java.util.List;

import com.example.tacos.domain.Order;
import com.example.tacos.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
    
}
