package com.example.tacocloudconsuming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableFeignClients
public class TacoCloudApiConsumeConfig {
    
    @Value("${tacocloud.api.url}")
    private String tacoCloudApiUrl;

    @Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	} 

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder()
    {
        return WebClient.builder();
    }
    
    @Bean
    public WebClient webClient()
    {
        return webClientBuilder().baseUrl(tacoCloudApiUrl).build();
    }
}
