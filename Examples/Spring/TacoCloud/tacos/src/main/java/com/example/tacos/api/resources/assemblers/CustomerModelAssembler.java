package com.example.tacos.api.resources.assemblers;

import com.example.tacos.api.controllers.OrderApiController;
import com.example.tacocloudmodels.CustomerModel;
import com.example.tacos.domain.Customer;
//import com.example.tacos.domain.reactive.mongo.Customer;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerModel> 
{

    public CustomerModelAssembler() {

        super(OrderApiController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(Customer entity) {

        return instantiateModel(entity);
    }
    
    @Override
    protected CustomerModel instantiateModel(Customer entity) {
      
        CustomerModel model = new CustomerModel();

        model.setCity(entity.getCity());
        model.setFullname(entity.getFullname());
        model.setId(entity.getId());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setState(entity.getState());
        model.setStreet(entity.getStreet());
        model.setZip(entity.getZip());

        return model;
    }
    
}
