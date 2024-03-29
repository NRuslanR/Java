package com.example.tacos.domain.reactive.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection = "ingredients")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 5137450564054759176L;
    
    public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE, UNKNOWN };

    @Id
    private final String id;

    private final String name;

    private final Type type;

}