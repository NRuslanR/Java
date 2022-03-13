package com.example.tacos.api.resources;

import java.util.Collection;
import java.util.Date;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class TacoModel extends RepresentationModel<TacoModel> {
    
    private long id;
    private Date createdAt;
    private String name;
    private Collection<IngredientModel> ingredientModels;

}
