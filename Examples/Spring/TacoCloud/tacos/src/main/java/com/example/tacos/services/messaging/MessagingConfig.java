package com.example.tacos.services.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import com.example.tacos.domain.Order;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
@EnableConfigurationProperties(MessagingProperties.class)
public class MessagingConfig {
    
    @Autowired
    private MessagingProperties messagingProperties;

    @Bean
    public Destination orderQueue()
    {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(messagingProperties.getOrdersDestination());

        return activeMQQueue;
    }

    @Bean
    public MappingJackson2MessageConverter jmsMessageConverter()
    {
        MappingJackson2MessageConverter converter =
                new MappingJackson2MessageConverter();

        converter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();

        typeIdMappings.put("order", Order.class);

        converter.setTypeIdMappings(typeIdMappings);
        
        return converter;
    }

    //@Bean("messageConverter")
    public MessageConverter amqpMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
