package com.example.tacos.services.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import com.example.tacos.domain.Order;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
public class MessagingConfig {
    
    @Bean
    public Destination orderQueue()
    {
        return new ActiveMQQueue("tacocloud.orders.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter()
    {
        MappingJackson2MessageConverter converter =
                new MappingJackson2MessageConverter();

        converter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();

        typeIdMappings.put("order", Order.class);

        converter.setTypeIdMappings(typeIdMappings);
        
        return converter;
    }
}
