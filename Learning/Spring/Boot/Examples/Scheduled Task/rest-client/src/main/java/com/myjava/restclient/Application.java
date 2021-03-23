/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ruslan
 */
@SpringBootApplication
public class Application {
    
    private Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder.build();
    }
    
    @Bean 
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception
    {
        return args -> {
            
            Quote quote = 
                    restTemplate.getForObject(
                            "https://quoters.apps.pcfone.io/api/random", 
                            Quote.class
                    );
            
            logger.info(quote.toString());
            
        };
    }                   
}
