package com.example.tacos.domain;

import java.util.List;

public class Taco {

    private String name;
    private List<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

}
