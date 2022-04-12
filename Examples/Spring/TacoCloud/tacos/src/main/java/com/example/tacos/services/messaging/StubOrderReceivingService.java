package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.stereotype.Service;

@Service(/*"orderReceivingService"*/)
public class StubOrderReceivingService implements OrderReceivingService {

    public StubOrderReceivingService()
    {

    }
    
    @Override
    public Order receiveOrder() throws Exception {
        
        return new Order();
    }
    
}
