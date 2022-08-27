package com.learning.hibernate.entities;

import java.util.Collection;

import com.learning.hibernate.values.PersonName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "person_id")
public class Customer extends Person 
{
    public Customer(PersonName name, Address address, Collection<Order> orders)
    {
        super(name, address);
        
        setOrders(orders);
    }
    
    @NonNull
    @OneToMany(
            cascade = { CascadeType.PERSIST },
            mappedBy = "customer", 
            orphanRemoval = true
    )
    private Collection<Order> orders;

    public void setOrders(Collection<Order> orders)
    {
        for (Order order : orders)
            order.setCustomer(this);

        this.orders = orders;
    }
}
