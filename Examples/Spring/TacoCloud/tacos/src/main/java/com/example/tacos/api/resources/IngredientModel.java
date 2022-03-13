package com.example.tacos.api.resources;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import lombok.Data;

@Data
public class IngredientModel extends RepresentationModel<IngredientModel> {
    
    private String id;
    private String name;
    private String type;

}
