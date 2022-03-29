package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JmsOrderListener {

    @JmsListener(destination = "{@orderQueue.queueName")
    public void receiveOrder(Order order)
    {
        log.info(order.toString());
    }
    
}
