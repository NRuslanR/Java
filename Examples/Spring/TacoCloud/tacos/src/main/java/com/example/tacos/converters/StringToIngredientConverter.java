package com.example.tacos.converters;

import com.example.tacos.domain.Ingredient;

import org.springframework.core.convert.converter.Converter;

import ch.qos.logback.core.subst.Token.Type;

public class StringToIngredientConverter implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(String source) {
        
        return new Ingredient(source, "", Ingredient.Type.UNKNOWN);
    }

}
