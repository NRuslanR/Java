package com.example.tacos.api.resources;

import java.util.Collection;
import java.util.Date;

import com.example.tacos.domain.User;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class OrderModel extends RepresentationModel<OrderModel> {
    
    private long id;
    private Date placedAt;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCvv;

    private Collection<TacoModel> tacoModels;

    private CustomerModel customerModel;
}
