package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.stereotype.Service;


public interface OrderMessagingService {
    
    void sendOrder(Order order);
    
}