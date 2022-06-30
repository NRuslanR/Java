package com.example.tacocloudmodels;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Relation(value = "ingredient", collectionRelation = "ingredients")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class IngredientModel extends RepresentationModel<IngredientModel> {
    
    private String id;
    private String name;
    private String type;

}
