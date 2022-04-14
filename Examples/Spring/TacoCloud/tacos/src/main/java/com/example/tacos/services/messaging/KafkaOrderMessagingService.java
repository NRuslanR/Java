package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("orderMessagingService")
public class KafkaOrderMessagingService implements OrderMessagingService{

    private MessagingProperties messagingProperties;
    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaOrderMessagingService(
        MessagingProperties messagingProperties,
        KafkaTemplate<String, Object> kafkaTemplate
    )
    {
        this.messagingProperties = messagingProperties;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @Override
    public void sendOrder(Order Object) {
        
        kafkaTemplate.send(messagingProperties.getOrdersDestination(), Object);

    }
    
}
