package com.example.tacos.services.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        
        configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
            "localhost:9092"
        );
          
        configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
            StringSerializer.class
        );
          
        configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
            JsonSerializer.class
        );
  
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> KafkaTemplate() {

        KafkaTemplate<String, Object> kafkaTemplate = 
                new KafkaTemplate<>(producerFactory());
            
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());

        return kafkaTemplate;
    }
}