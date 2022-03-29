package com.example.tacos.api.consume;

import java.net.URI;
import java.util.Arrays;

import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Ingredient.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class TacoApiConsumer {

    @Qualifier(value = "restTemplate")
    private final RestTemplate restTempl;

    private UriComponentsBuilder uriBuilder;

    @Qualifier("baseTacoApiPath")
    private URI basePath;

    private Traverson traverson;

    @Autowired
    public TacoApiConsumer(
            RestTemplate restTemplate,
            URI basePath,
            UriComponentsBuilder uriBuilder,
            Traverson traverson
    ) {
        this.restTempl = restTemplate;
        this.basePath = basePath;
        this.uriBuilder = uriBuilder;
        this.traverson = traverson;

    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeIngredients() {
        String ingredientsPath = uriBuilder
                .uri(basePath)
                .pathSegment("ingredients")
                .toUriString();

        ResponseEntity<PagedModel<Ingredient>> ingredientsModel = restTempl.exchange(
                ingredientsPath,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedModel<Ingredient>>() {
                });

        PagedModel<Ingredient> content = ingredientsModel.getBody();

        Ingredient[] ingredients = content.getContent().toArray(new Ingredient[] {});

        log.info("content:" + Arrays.toString(ingredients));
        log.info("headers:" + ingredientsModel.getHeaders());
    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeIngredientById() {
        String ingredientId = "LETC";

        URI ingredientPath = uriBuilder
                .uri(basePath)
                .pathSegment("ingredients", "{id}")
                .buildAndExpand(ingredientId)
                .toUri();

        ResponseEntity<Ingredient> ingredient = restTempl.getForEntity(ingredientPath, Ingredient.class);

        log.info("Ingredient: " + ingredient.getBody());
        log.info("Ingredient heads: " + ingredient.getHeaders());
    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeUpdateIngredient() {
        Ingredient ingredient = new Ingredient("JACK", "Monterry", Type.CHEESE);

        URI ingredientPath = uriBuilder
                .uri(basePath)
                .pathSegment("ingredients", "{id}")
                .buildAndExpand(ingredient.getId())
                .toUri();

        restTempl.put(ingredientPath, ingredient);

        log.info("put ingredient");
    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeDeleteIngredient() {
        URI ingredientPath = uriBuilder
                .uri(basePath)
                .pathSegment("ingredients", "{id}")
                .buildAndExpand("TEST")
                .toUri();

        try {

            restTempl.delete(ingredientPath);

            log.info("delete ingredient");

        } catch (Exception e) {

        }
        
    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeCreateIngredient() {
        String ingredientsPath = uriBuilder
                .uri(basePath)
                .pathSegment("ingredients")
                .toUriString();

        Ingredient newIngredient = new Ingredient("TEST", "Test Ingred", Type.PROTEIN);

        ResponseEntity<Ingredient> postedIngredient = restTempl.postForEntity(ingredientsPath, newIngredient,
                Ingredient.class);

        log.info("New ingredient: " + postedIngredient.getBody());
        log.info("New ingredient location: " + postedIngredient.getHeaders().getLocation());
    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void consumeRecentTacos()
    {
        
        Ingredient ingredient =
            traverson
                .follow("tacos")
                .follow("$._embedded.tacos[0].ingredients.links.self.href")
                .follow("$._embedded.ingredients[0]._links.self.href")
                .toObject(Ingredient.class);
    
                    
        log.info(ingredient.toString());
    }
}
