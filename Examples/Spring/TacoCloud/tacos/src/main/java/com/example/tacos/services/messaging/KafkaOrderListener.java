package com.example.tacos.services.messaging;

import com.example.tacos.domain.Order;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaOrderListener {
    
    @KafkaListener(topics = { "tacocloud.orders.dest" }, groupId = "tacocloud_group")
    public void receiveOrder(ConsumerRecord<String, Order> consumerRecord)
    {
        log.info(
            "Listened for order from partition {} and timestamp {}: {}",
            consumerRecord.partition(), 
            consumerRecord.timestamp(), 
            consumerRecord.value()
        );
    }
}
