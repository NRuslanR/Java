package com.example.tacos.data.jpa.reactive;

import com.example.tacos.domain.reactive.mongo.Ingredient;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ingredientRepo")
public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {
    
}
