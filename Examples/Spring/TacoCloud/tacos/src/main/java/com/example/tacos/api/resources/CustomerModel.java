package com.example.tacos.api.resources;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CustomerModel extends RepresentationModel<CustomerModel> {
    
    private long id;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

}
