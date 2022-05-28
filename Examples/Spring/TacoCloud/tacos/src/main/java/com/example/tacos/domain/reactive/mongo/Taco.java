package com.example.tacos.domain.reactive.mongo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@RestResource(rel = "tacos", path = "tacos")
@Document(collection = "tacos")
public class Taco implements Serializable {

    private static final long serialVersionUID = 5877377659834274594L;
    
    @Id
    private Long id;

    private Date createdAt = new Date();
    
    @NotNull
    @Size(min = 5, message = "Taco name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "Ingredient list must contain at least one ingredient")
    private List<Ingredient> ingredients;

    public Taco()
    {
        ingredients = new ArrayList<>();
    }
    
    public void addIngredient(Ingredient ingredient)
    {
        ingredients.add(ingredient);
    }

    public void addIngredients(Collection<Ingredient> ingredients)
    {
        this.ingredients.addAll(ingredients);
    }
}