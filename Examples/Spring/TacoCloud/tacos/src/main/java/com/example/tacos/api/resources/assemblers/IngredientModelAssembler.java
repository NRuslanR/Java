package com.example.tacos.api.resources.assemblers;

import com.example.tacos.api.controllers.IngredientApiController;
import com.example.tacocloudmodels.IngredientModel;
import com.example.tacos.domain.Ingredient;
//import com.example.tacos.domain.reactive.mongo.Ingredient;

import org.springframework.hateoas.CollectionModel;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

    public IngredientModelAssembler() {
        super(IngredientApiController.class, IngredientModel.class);
    }

    @Override
    public CollectionModel<IngredientModel> toCollectionModel(Iterable<? extends Ingredient> entities) {
    
        CollectionModel<IngredientModel> ingredientsModel = super.toCollectionModel(entities);

        return ingredientsModel;
    }

    @Override
    public IngredientModel toModel(Ingredient entity) {
        
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected IngredientModel instantiateModel(Ingredient entity) {
        
        IngredientModel model = new IngredientModel();

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setType(entity.getType().toString());

        return model;
    }

    
}
