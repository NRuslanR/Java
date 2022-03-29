package com.example.tacos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;
    
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

    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Not a valid credit card CVV")
    private String ccCvv;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(
        name = "orders_tacos",
        joinColumns = 
        {
            @JoinColumn(name = "order_id", referencedColumnName = "id"),
        },
        inverseJoinColumns = 
        {
            @JoinColumn(name = "taco_id", referencedColumnName = "id")
        }
    )
    private List<Taco> tacos;

    @ManyToOne(targetEntity = User.class)
    private User user;
    
    @PrePersist
    void placedAt()
    {
        this.placedAt = new Date();
    }
}
