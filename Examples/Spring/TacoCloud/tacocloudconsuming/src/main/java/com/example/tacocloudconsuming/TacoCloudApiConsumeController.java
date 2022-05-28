package com.example.tacocloudconsuming;

import com.example.tacocloudmodels.IngredientModel;
import com.example.tacocloudmodels.TacoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tacocloud")
@CrossOrigin(origins = "*")
public class TacoCloudApiConsumeController {
    
    private RestTemplate restTemplate;
    private WebClient webClient;

    @Autowired
    public TacoCloudApiConsumeController(
            @LoadBalanced RestTemplate restTemplate,
            WebClient webClient
    )
    {
        this.restTemplate = restTemplate;
        this.webClient = webClient;

    }

    @GetMapping("/ingredients")
    public ResponseEntity<CollectionModel<IngredientModel>> GetAllIngredients()
    {
        return restTemplate.exchange(
            "http://tacocloud-service/api/ingredients/",
            HttpMethod.GET,
            null,
                new ParameterizedTypeReference<CollectionModel<IngredientModel>>() {
                
            })
        ;
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<IngredientModel> GetIngredientById(@PathVariable("id") String id)
    {
        return restTemplate.exchange(
                "http://tacocloud-service/api/ingredients/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<IngredientModel>() {
                },
                id);
    }

    @GetMapping("/tacos/async")
    public Mono<ResponseEntity<CollectionModel<TacoModel>>> GetAllTacosAsync()
    {
        return webClient
                .get()
                .uri("/design/async")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CollectionModel<TacoModel>>() {

                });
    }
    
    @GetMapping("/tacos/{id}/async")
    public Mono<ResponseEntity<TacoModel>> GetTacoByIdAsync(@PathVariable("id") long id)
    {
        return
            webClient
            .get()
            .uri("/design/{id}/async", id)
            .retrieve()
            .toEntity(new ParameterizedTypeReference<TacoModel>() {
                
                    });
    }
}
