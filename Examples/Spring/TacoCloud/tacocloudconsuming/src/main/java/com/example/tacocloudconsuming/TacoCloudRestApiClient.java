package com.example.tacocloudconsuming;

import com.example.tacocloudmodels.IngredientModel;
import com.example.tacocloudmodels.TacoModel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;

@FeignClient(name = "tacocloud-service", path = "api")
public interface TacoCloudRestApiClient {
    
    @GetMapping("/ingredients")
    ResponseEntity<CollectionModel<IngredientModel>> GetAllIngredients();

    @GetMapping("/ingredients/{id}")
    ResponseEntity<IngredientModel> GetIngredientById(@PathVariable("id") String id);

}
