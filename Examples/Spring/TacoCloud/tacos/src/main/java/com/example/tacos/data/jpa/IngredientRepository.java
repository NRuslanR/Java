package com.example.tacos.data.jpa;

import com.example.tacos.domain.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    
}
