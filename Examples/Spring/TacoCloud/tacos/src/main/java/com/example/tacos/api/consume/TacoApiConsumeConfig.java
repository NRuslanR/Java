package com.example.tacos.api.consume;

import java.net.URI;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan
@Slf4j
public class TacoApiConsumeConfig {
    
    @Bean(value = "restTemplate")
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Bean
    public UriComponentsBuilder uriBuilder()
    {
        log.info("uriBuilder called");

        return UriComponentsBuilder.newInstance();
    }

    @Bean("baseTacoApiPath")
    public URI basePath()
    {
        return 
            uriBuilder()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("api/v2")
                .build()
                .toUri();
    }

    @Bean
    public Traverson traverson()
    {
        return new Traverson(basePath(), MediaTypes.HAL_JSON);
    }
}
