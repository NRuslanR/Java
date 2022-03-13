package com.example.tacos.api.resources.assemblers;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.tacos.api.controllers.DesignTacoApiController;
import com.example.tacos.api.resources.IngredientModel;
import com.example.tacos.api.resources.TacoModel;
import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

    private final IngredientModelAssembler ingredientModelAssembler;

    @Autowired
    public TacoModelAssembler(IngredientModelAssembler ingredientModelAssembler) {
        super(DesignTacoApiController.class, TacoModel.class);

        this.ingredientModelAssembler = ingredientModelAssembler;
    }

    @Override
    public CollectionModel<TacoModel> toCollectionModel(Iterable<? extends Taco> entities) {
  
        return super.toCollectionModel(entities);
    }

    @Override
    public TacoModel toModel(Taco entity) {
        
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected TacoModel instantiateModel(Taco entity) {
        
        TacoModel model = new TacoModel();

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCreatedAt(entity.getCreatedAt());
      
        Collection<IngredientModel> ingredientModels =
            entity
                .getIngredients()
                    .stream()
                        .map(i -> ingredientModelAssembler.toModel(i))
                        .collect(Collectors.toList());

        model.setIngredients(ingredientModels);
        
        return model;
    }

}
