package com.example.tacocloudconsuming;

import com.example.tacocloudmodels.IngredientModel;
import com.example.tacocloudmodels.TacoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tacocloud")
public class TacoCloudApiConsumeFeignController {
    
    private TacoCloudRestApiClient client;

    @Autowired
    public TacoCloudApiConsumeFeignController(TacoCloudRestApiClient client)
    {
        this.client = client;

    }

    @GetMapping("/ingredients")
    public ResponseEntity<CollectionModel<IngredientModel>> GetAllIngredients()
    {
        return client.GetAllIngredients();
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<IngredientModel> GetIngredientById(@PathVariable("id") String id)
    {
        return client.GetIngredientById(id);
    }

}
