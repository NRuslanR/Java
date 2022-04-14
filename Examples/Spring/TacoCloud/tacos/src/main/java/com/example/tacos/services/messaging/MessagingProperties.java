package com.example.tacos.services.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "taco.messaging")
@Component(value = "messagingProperties")
@Data
public class MessagingProperties {

    @Value("${taco.messaging.destinations.orders}")
    private String ordersDestination; 
    
}
