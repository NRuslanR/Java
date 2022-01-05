package com.example.tacos.domain;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class Ingredient {

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    };

    private final String id;
    private final String name;
    private final Type type;

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}
