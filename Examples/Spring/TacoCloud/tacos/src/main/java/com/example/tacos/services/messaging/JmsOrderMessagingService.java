package com.example.tacos.services.messaging;

import javax.jms.Destination;

import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service(/*value = "orderMessagingService"*/)
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue)
    {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    @Override
    public void sendOrder(Order order) {
        
        jms.convertAndSend(
            orderQueue, 
            order,
                msg -> {
                    msg.setStringProperty("X_ORDER_SOURCE", "WEB");
                    return msg;
                }
        );
        
    }
    
}
