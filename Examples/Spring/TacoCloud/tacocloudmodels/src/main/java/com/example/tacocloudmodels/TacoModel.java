package com.example.tacocloudmodels;

import java.util.Collection;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Data;

@Data
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {
    
    private long id;
    private Date createdAt;
    private String name;

    private Collection<IngredientModel> ingredients;

}
