/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
/**
 *
 * @author ruslan
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne 
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;
    
    private int quantity;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public Product getProduct()
    {
        return product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    public Order getOrder()
    {
        return order;
    }
    
    public void setOrder(Order order)
    {
        this.order = order;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
}
