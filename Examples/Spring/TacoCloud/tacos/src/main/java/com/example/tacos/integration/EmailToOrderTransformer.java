package com.example.tacos.integration;

import javax.mail.Message;

import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<String> {

    @Override
    protected AbstractIntegrationMessageBuilder<String> doTransform(Message mailMessage) throws Exception {
        
        return MessageBuilder.withPayload(mailMessage.toString());
    }
    
}
