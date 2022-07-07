package com.example.tacos.monitor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Service;

import com.example.tacos.data.jpa.IngredientRepository;
import com.example.tacos.data.jpa.OrderRepository;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.data.jpa.UserRepository;

@Service
public class TacoCloudInfoContributor implements InfoContributor {

    private TacoRepository tacoRepository;
    private IngredientRepository ingredientRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public TacoCloudInfoContributor(
        TacoRepository tacoRepository,
        IngredientRepository ingredientRepository,
        OrderRepository orderRepository,
            UserRepository userRepository
    )
    {

        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void contribute(Builder builder) {
        
        Map<String, String> authorContacts = 
            Map.of(
                "email", "nruslanr95@gmail.com",
                "telegram", "NearHimSelfInquerer"
            );

        Map<String, Object> authorInfo = 
            Map.ofEntries(
                Map.entry("firstName", "Ruslan"),
                Map.entry("lastName", "Nigmatullin"),
                Map.entry("contacts", authorContacts)
                );

        Map<String, Object> appStats = 
            Map.of(
                "ingredientCount", ingredientRepository.count(), 
                "tacoCount", tacoRepository.count(), 
                "orderCount", orderRepository.count(), 
                "userCount", userRepository.count()
            );
            
        Map<String, Object> appInfo = 
            Map.ofEntries(
                Map.entry("name", "TacoCloud by Spring Boot"),
                Map.entry("version", "1.0.0"),
                Map.entry("description", "Application for Spring Boot learning"),
                Map.entry("author", authorInfo),
                Map.entry("statistics", appStats)
                );
            
        builder.withDetail("app-info", appInfo);
    }
    
}
