package com.example.tacos.integration;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderSubmitMessageHandler implements GenericHandler<String>  {

    @Override
    public Object handle(String payload, MessageHeaders headers) {
       
        log.info(payload);
        
        return null;
    }
    
}
