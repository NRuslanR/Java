package com.learning.hibernate.examples;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

import org.hibernate.Session;

import com.learning.hibernate.entities.CategorizedProduct;
import com.learning.hibernate.entities.ProductCategory;
import com.learning.hibernate.entities.ShoppingCart;
import com.learning.hibernate.entities.ShoppingCartItem;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;

public class FetchModeExample extends HibernateExample {

    public FetchModeExample() {
        super("FetchMode");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        prepareData(session);
        runJoinFetchModeExample(session);
        runSubSelectFetchModeExample(session);
        
    }

    private void prepareData(Session session) {

        final int itemCount = 10, childItemCount = 5;

        Collection<Object> itemsToBePrepared = new ArrayList<>(
            IntStream.rangeClosed(1, itemCount).boxed().map(i -> 
                new ProductCategory(
                    "Category" + i, 
                    IntStream
                    .rangeClosed(1, childItemCount)
                    .boxed()
                    .map(j -> 
                        new CategorizedProduct("Product" + j)
                    ).toList()
                )
            ).toList()
        );

        itemsToBePrepared.addAll(
            IntStream
            .rangeClosed(1, itemCount)
            .boxed()
            .map(i -> 
                new ShoppingCart(
                    "Cart" + i, 
                    IntStream
                    .rangeClosed(1, childItemCount)
                    .boxed()
                    .map(j -> ShoppingCartItem.of(34, "Product" + j, Money.ofUSD(45)))
                    .toList()
                )
            ).toList()
        );

        HibernateUtils.persistInTransaction(session, itemsToBePrepared);

        session.clear();
    }

    private void runJoinFetchModeExample(Session session) {

        ShoppingCart cart = fetchEntityById(session, 100, ShoppingCart.class);
        
        out.printf("Cart fetched by join fetch mode: %n%s%n", cart);
  
    }

    private void runSubSelectFetchModeExample(Session session) {

        Collection<ProductCategory> categories = 
            session
                .createQuery(
                    "select pc from ProductCategory as pc", 
                    ProductCategory.class
                )
                .getResultList();

        printEntities(categories, "categories fetched by subselect fetch mode");
    }
    
}
