package com.example.tacos.domain.reactive.mongo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//import com.example.tacos.domain.Taco;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 3L;
    
    public Order()
    {
        tacos = new ArrayList<>();
    }

    public void addDesign(Taco taco)
    {
        tacos.add(taco);
    }

    @Id
    private Long id;

    private Date placedAt = new Date();
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip is required")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/[1-9][0-9]$", message = "Not a valid credit card expiration")
    private String ccExpiration;
    
    @Digits(integer = 3, fraction = 0, message = "Not a valid credit card CVV")
    private String ccCvv;

    private List<Taco> tacos;

    private Customer customer;
    
}