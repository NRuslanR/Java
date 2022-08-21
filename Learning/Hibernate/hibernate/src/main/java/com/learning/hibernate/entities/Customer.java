package com.learning.hibernate.entities;

import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends ExampleEntity 
{
    @NonNull
    private String firstName;
    
    @NonNull
    private String lastName;
    
    @Transient
    private String fullName;
    
    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Collection<Order> orders;

}
