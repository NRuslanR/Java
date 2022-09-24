package com.learning.hibernate.examples;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import com.learning.hibernate.entities.Book;
import com.learning.hibernate.entities.ShoppingCart;
import com.learning.hibernate.entities.ShoppingCartItem;
import com.learning.hibernate.entities.SpiderMan;
import com.learning.hibernate.entities.Spruce;
import com.learning.hibernate.entities.Spruce.ConeType;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.BookId;
import com.learning.hibernate.values.Money;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class BatchOperationsExample extends HibernateExample {

    public BatchOperationsExample() {
        super("BatchOperations");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception 
    {
        runBatchlessExample(session);
        runExampleWithBatchingAndImplicitFlush(session);
        runExampleWithBatchingAndExplicitFlush(session);
        runExampleWithBatchingIntoMultipleTables(session);
        runExampleWithUpdateBatching(session);
    }

    private void runBatchlessExample(Session session) {

        final int itemCount = 10;

        session.setJdbcBatchSize(0);

        HibernateUtils.wrapInTransaction(session, () -> {
        
            for (int i = 1; i <= itemCount; ++i)
                session.persist(new Spruce("Spurce" + i, 12 + i, ConeType.Green));

        });
    
    }

    private void runExampleWithBatchingAndImplicitFlush(Session session) {

        final int itemCount = 20;

        session.setJdbcBatchSize(5);

        HibernateUtils.wrapInTransaction(session, () -> {

            for (int i = 1; i <= itemCount; ++i)
                session.persist(new SpiderMan("SpiderMan" + i, 20 + i));

        });
    }

    private void runExampleWithBatchingAndExplicitFlush(Session session) {

        final int itemCount = 30, batchSize = 6;

        session.setJdbcBatchSize(batchSize);

        HibernateUtils.wrapInTransaction(session, () -> {

            for(int i = 1; i <= itemCount; ++i)
            {
                session.persist(
                    new Book(
                        BookId.of("author" + i, "title" + i), 
                        ZonedDateTime.of(
                            LocalDateTime.now(), 
                            ZoneId.systemDefault()
                        )
                    )
                );

                if (i % batchSize == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
            
        });
    }

    private void runExampleWithBatchingIntoMultipleTables(Session session) {

        final int batchSize = 5;

        session.setJdbcBatchSize(batchSize);

        HibernateUtils.wrapInTransaction(session, () -> {

            final int parentItemCount = 15, childItemCount = 5;

            ShoppingCart cart;
            Collection<ShoppingCartItem> cartItems;

            for (int i = 1; i <= parentItemCount; ++i) {
                cartItems = new ArrayList<>(childItemCount);

                for (int j = 1; j <= childItemCount; ++j) {
                    cartItems.add(
                            new ShoppingCartItem(
                                    i + j,
                                    String.format("Product%d%d", i, j),
                                    Money.ofUSD(20 + i + j)));
                }

                cart = new ShoppingCart("Cart" + i);

                cart.setItems(cartItems);

                session.persist(cart);

                if (i % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }
        });
    }
    
    private void runExampleWithUpdateBatching(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ShoppingCartItem> cq = cb.createQuery(ShoppingCartItem.class);
        Root<ShoppingCartItem> cartItemsRoot = cq.from(ShoppingCartItem.class);

        cq.select(cartItemsRoot).where(
            cb.and(
                cb.greaterThan(
                    cartItemsRoot.get("quantity"), 0
                ), 
                cb.isNotNull(cartItemsRoot.get("productName"))
            )
        );

        List<ShoppingCartItem> cartItems = session.createQuery(cq).getResultList();

        session.setJdbcBatchSize(7);
        
        HibernateUtils.wrapInTransaction(session, () -> {

            cartItems.forEach(ci -> ci.setProductName(ci.getProductName() + " (UPD)"));

        });
    }


}
