package com.example.tacos.services.messaging;

import javax.jms.Destination;

import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderReceiver implements OrderReceivingService {

    private final JmsTemplate jms;

    @Qualifier("orderQueue")
    private final Destination destination;

    private MessageConverter messageConverter;

    @Autowired
    public JmsOrderReceiver(
        JmsTemplate jms,
        Destination destination,
        MessageConverter messageConverter
    )
    {
        this.jms = jms;
        this.destination = destination;
        this.messageConverter = messageConverter;

    }

    @Override
    public Order receiveOrder() throws Exception {
        
        return (Order) jms.receiveAndConvert(destination);
    }
    

}
