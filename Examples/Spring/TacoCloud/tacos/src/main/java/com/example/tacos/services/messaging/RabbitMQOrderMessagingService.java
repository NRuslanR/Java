package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderMessagingService")
public class RabbitMQOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    @Autowired
    public RabbitMQOrderMessagingService(
        MessagingProperties messagingProperties,
        RabbitTemplate rabbitTemplate)
    {
        this.messagingProperties = messagingProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        
        sendOrderMessageWithConvert(order);
    }
    
    private void sendOrderMessageWithConvert(Order order) {

        rabbitTemplate.convertAndSend(
            messagingProperties.getOrdersDestination(),
            order, 
            message -> {

                MessageProperties properties = message.getMessageProperties();

                properties.setHeader("X_ORDER_SOURCE", "WEB");

                return message;
            }
        );
    }

    private void sendOrderMessageWithManualConversion(Order order)
    {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        
        MessageProperties properties = new MessageProperties();

        Message message = messageConverter.toMessage(order, properties);

        rabbitTemplate.send(messagingProperties.getOrdersDestination(), message);
    }
}
