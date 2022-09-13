package com.learning.hibernate.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "orders")
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
@NamedQueries(value = {
    @NamedQuery(
        name = Order.FIND_ALL_ORDERS_AT_CREATION_DATE,
        query = "select o from Order o where o.creationDate = ?1"
    ),
    @NamedQuery(
        name = Order.FIND_ALL_ORDERS_BY_CUSTOMER,
        query = "select o from Order o where " +
        "o.customer.id = :id"
    )
})
public class Order extends ExampleEntity {
    
    public static final String FIND_ALL_ORDERS_AT_CREATION_DATE = "Order.findAllOrdersAtCreationDate";
    public static final String FIND_ALL_ORDERS_BY_CUSTOMER = "Order.findAllOrdersByCustomer";
    
    @Basic
    @NonNull
    private String name;

    @NonNull
    @OneToMany(cascade = { CascadeType.PERSIST }, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @NonNull
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "person_id")
    @ToString.Exclude
    private Customer customer;
}
