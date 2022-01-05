package com.example.tacos.data;

import com.example.tacos.domain.Ingredient;

public interface IngredientRepository {
    
    Iterable<Ingredient> findAll();

    Ingredient findOne(Long id);

    Ingredient save(Ingredient ingredient);
}
