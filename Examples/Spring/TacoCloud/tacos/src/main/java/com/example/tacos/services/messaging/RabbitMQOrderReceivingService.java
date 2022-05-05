package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service("orderReceivingService")
public class RabbitMQOrderReceivingService implements OrderReceivingService {

    private MessagingProperties messagingProperties;
    private RabbitTemplate rabbitTemplate;
    private MessageConverter messageConverter;

    public RabbitMQOrderReceivingService(
            MessagingProperties messagingProperties,
            RabbitTemplate rabbitTemplate) {
        this.messagingProperties = messagingProperties;
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = rabbitTemplate.getMessageConverter();
    }

    @Override
    public Order receiveOrder() throws Exception {

        return receiveWithAutoConversion();
    }

    private Order receiveWithAutoConversion() {

        return rabbitTemplate.receiveAndConvert(
                messagingProperties.getOrdersDestination(),
                new ParameterizedTypeReference<Order>() {
                });
    }

    private Order receiveOrderWithManualConversion() {

        Message message = rabbitTemplate.receive(messagingProperties.getOrdersDestination());

        return (message != null) ? (Order) messageConverter.fromMessage(message) : null;
    }

}
