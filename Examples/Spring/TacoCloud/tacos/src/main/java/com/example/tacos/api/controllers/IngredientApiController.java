package com.example.tacos.api.controllers;

import java.util.Optional;

import com.example.tacos.api.resources.IngredientModel;
import com.example.tacos.api.resources.assemblers.IngredientModelAssembler;
import com.example.tacos.data.jpa.IngredientRepository;
import com.example.tacos.domain.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/ingredients")
public class IngredientApiController {
    
    private final IngredientRepository ingredientRepository;
    private final IngredientModelAssembler ingredientModelAssembler;

    @Autowired
    public IngredientApiController(
        IngredientRepository ingredientRepository,
        IngredientModelAssembler ingredientModelAssembler
    )
    {
        this.ingredientRepository = ingredientRepository;
        this.ingredientModelAssembler = ingredientModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<IngredientModel>> GetAllIngredients()
    {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        CollectionModel<IngredientModel> model = 
            ingredientModelAssembler.toCollectionModel(ingredients);

        model.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                    IngredientApiController.class)
                        .GetAllIngredients()
                )
                .withSelfRel()
        );

        return ResponseEntity.ok(model);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<IngredientModel> GetIngredientById(@PathVariable("id") String id)
    {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);

        if (ingredient.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ingredientModelAssembler.toModel(ingredient.get()));
    }
}
