package com.example.tacos.data.jpa;

import com.example.tacos.domain.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    
}
