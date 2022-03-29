package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

public interface OrderReceivingService {
    
    public Order receiveOrder() throws Exception;
}
