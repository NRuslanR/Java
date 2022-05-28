package com.example.tacos.data.reactive.mongodb;

import java.util.Map;

import com.example.tacos.domain.reactive.mongo.Customer;
import com.example.tacos.domain.reactive.mongo.Ingredient;
import com.example.tacos.domain.reactive.mongo.Order;
import com.example.tacos.domain.reactive.mongo.Taco;
import com.example.tacos.domain.reactive.mongo.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoEntityConfig {
    
    @Bean
    public Map<String, String> entitySequenceNames()
    {
        return 
            Map.of(
                Ingredient.class.getSimpleName(), Ingredient.class.getSimpleName(), 
                Taco.class.getSimpleName(), Taco.class.getSimpleName(),
                Order.class.getSimpleName(), Order.class.getSimpleName(),
                Customer.class.getSimpleName(), Customer.class.getSimpleName(),
                User.class.getSimpleName(), User.class.getSimpleName()
            );
    }
}
