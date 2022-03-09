package com.example.tacos.converters;

import com.example.tacos.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;

public class StringToIngredientConverter implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(String source) {
        
        return new Ingredient(source, "", Ingredient.Type.UNKNOWN);
    }

}
