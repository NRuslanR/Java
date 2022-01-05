package com.example.tacos.data;

import com.example.tacos.domain.Order;

public interface OrderRepository {
    
    Order save(Order order);
}
