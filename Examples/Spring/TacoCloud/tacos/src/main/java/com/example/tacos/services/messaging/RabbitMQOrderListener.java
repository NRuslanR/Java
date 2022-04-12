package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQOrderListener {

    @RabbitListener(queues = { "#{messagingProperties.getOrdersDestination()}" })
    public void receiveOrder(Order order)
    {
        log.info("Listened for order: " + order);
    }
    
}
