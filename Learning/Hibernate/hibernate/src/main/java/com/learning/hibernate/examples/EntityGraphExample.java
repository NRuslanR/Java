package com.learning.hibernate.examples;

import java.util.Collection;
import java.util.stream.IntStream;

import org.hibernate.Session;
import org.hibernate.stat.internal.SessionStatisticsImpl;

import com.learning.hibernate.entities.ShoppingCart;
import com.learning.hibernate.entities.ShoppingCartItem;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;

import jakarta.persistence.EntityGraph;

public class EntityGraphExample extends HibernateExample {

    public EntityGraphExample() {
        super("EntityGraph");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        prepareData(session);
        runEntityGraphExample(session);
        
    }

    private void prepareData(Session session) {

        final int itemCount = 5, childItemCount = 5;

        Collection<ShoppingCart> cartsToBePrepared =
            IntStream
                .rangeClosed(1, itemCount)
                .boxed()
                .map(i -> 
                    new ShoppingCart(
                        "Cart" + i, 
                        IntStream
                            .rangeClosed(1, childItemCount)
                            .boxed()
                            .map(j -> 
                                ShoppingCartItem.of(
                                    23, "Product" + j, Money.ofUSD(43
                                ))
                            ).toList()
                    )
                        ).toList();
                
        HibernateUtils.persistInTransaction(session, cartsToBePrepared);
    }

    private void runEntityGraphExample(Session session) {

        EntityGraph rootGraph = session.getEntityGraph("shopping-cart-graph");

        Collection<ShoppingCart> carts = 
            session
                .createQuery(
        "select sc from ShoppingCart sc",
                ShoppingCart.class
                )
                .setHint("jakarta.persistence.loadgraph", rootGraph)
                .getResultList();
        
        printEntities(carts, "carts fetched by entity load-based graph");

        carts =
            session
                .createQuery(
                    "select sc from ShoppingCart sc", ShoppingCart.class
                ).setHint("jakarta.persistence.fetchgraph", rootGraph
                        ).getResultList();

        printEntities(carts, "carts fetched by entity fetch-based graph");
    }
    
}
