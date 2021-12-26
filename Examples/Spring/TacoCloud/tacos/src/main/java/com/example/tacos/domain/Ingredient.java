package com.example.tacos.domain;

public class Ingredient {
    
    public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE };

    private final String id;
    private final String name;
    private final Type type;

    public Ingredient(String id, String name, Type type)
    {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
}
