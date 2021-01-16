/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.entities;

import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import java.io.StringWriter;
/**
 *
 * @author ruslan
 */
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public List<OrderItem> getOrderItems()
    {
        return orderItems;
    }
    
    @Override
    public String toString()
    {
        try {
            
            return new ObjectMapper().writeValueAsString(this);
            
        } 
        
        catch (Exception e) {
            
            return "Incorrect Representation";
        }
        
    }
}
