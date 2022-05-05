package com.example.tacos.api.consume;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DesignTacoApiControllerConsumeConfig {
    
    @Value(value = "${server.host}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Bean
    public WebClient webClient()
    {
        return WebClient.create(String.format("http://%s:%d/api", host, port));
    }
}
