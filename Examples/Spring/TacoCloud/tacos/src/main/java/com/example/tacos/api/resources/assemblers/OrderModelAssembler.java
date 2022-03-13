package com.example.tacos.api.resources.assemblers;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.tacos.api.controllers.OrderApiController;
import com.example.tacos.api.resources.CustomerModel;
import com.example.tacos.api.resources.OrderModel;
import com.example.tacos.api.resources.TacoModel;
import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel>
{
    private final TacoModelAssembler tacoModelAssembler;
    private final CustomerModelAssembler customerModelAssembler;

    @Autowired
    public OrderModelAssembler(
        TacoModelAssembler tacoModelAssembler,
        CustomerModelAssembler customerModelAssembler
    ) {

        super(OrderApiController.class, OrderModel.class);

        this.tacoModelAssembler = tacoModelAssembler;
        this.customerModelAssembler = customerModelAssembler;
    }

    @Override
    public CollectionModel<OrderModel> toCollectionModel(Iterable<? extends Order> entities) {
  
        return super.toCollectionModel(entities);
    }

    @Override
    public OrderModel toModel(Order entity) {
      
        return createModelWithId(entity.getId(), entity);
    }
        
    @Override
    protected OrderModel instantiateModel(Order entity) {
       
        OrderModel model = new OrderModel();

        model.setCcCvv(entity.getCcCvv());
        model.setCcExpiration(entity.getCcExpiration());
        model.setCcNumber(entity.getCcNumber());
        model.setCity(entity.getCity());

        CustomerModel customerModel = customerModelAssembler.toModel(entity.getUser());

        model.setCustomer(customerModel);

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPlacedAt(entity.getPlacedAt());
        model.setState(entity.getState());
        model.setStreet(entity.getStreet());
        
        Collection<TacoModel> tacoModels = 
            entity
                .getTacos()
                    .stream()
                        .map(t -> tacoModelAssembler.toModel(t))
                        .collect(Collectors.toList());

        model.setTacos(tacoModels);

        return model;
    }
}
