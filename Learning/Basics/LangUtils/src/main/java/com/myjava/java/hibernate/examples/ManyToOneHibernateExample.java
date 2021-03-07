/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.examples;

import com.myjava.java.hibernate.entities.Order;
import com.myjava.java.hibernate.entities.OrderItem;
import com.myjava.java.hibernate.entities.Product;
import java.math.BigInteger;
import static java.lang.System.out;

/**
 *
 * @author ruslan
 */
public class ManyToOneHibernateExample extends HibernateExample {
    
    public ManyToOneHibernateExample()
    {
        super();
        
        setGlobalTransactionEnabled(false);
    }
    
    @Override
    public void doRun(Object... args)
    {
        super.doRun(args);
        
        Order order = new Order();
        
        OrderItem orderItem = new OrderItem();
        
        Product product = new Product();
        
        product.setName("Scorn Game First-Person Shooter");
        product.setPrice(BigInteger.valueOf(4235));
        
        orderItem.setQuantity(25);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        
        order.getOrderItems().add(orderItem);
        
        orderItem = new OrderItem();
        
        product = new Product();
        
        product.setName("Tale about those who play with existence");
        product.setPrice(BigInteger.valueOf(2324));
        
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(5);
        
        order.getOrderItems().add(orderItem);
        
        wrapInTransaction(() -> {
            
            entityManager.persist(order);
            
            
            for (OrderItem oi : order.getOrderItems())
            {
                entityManager.persist(oi.getProduct());                
                entityManager.persist(oi);
            }
                                        
        });
        
        out.printf("Created order:%n%s%n", order);
        
        Order savedOrder = entityManager.find(Order.class, order.getId());
        
        out.printf("Saved Order:%n%s%n", savedOrder);
        
        wrapInTransaction(
            () -> {
                
                for (OrderItem oi : order.getOrderItems())
                {
                    entityManager.remove(oi);
                    entityManager.remove(oi.getProduct());
                }
                
                entityManager.remove(order);
                
            }
        );
        
        out.printf("Saved Order Removed ? %b%n", IsOrderRemoved(order.getId()));
    }

    private boolean IsOrderRemoved(Object orderId) {
        
        return entityManager.find(Order.class, orderId) == null;
    }
}
