package com.learning.hibernate.examples;

import static java.lang.System.out;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;

import com.learning.hibernate.entities.Product;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;

public class EntityLifeCycleExample extends HibernateExample {

    public EntityLifeCycleExample()
    {
        super("EntityLifeCycle");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
   
        doEntitiesSelection(session);
        doEntitiesPersisting(session);
        doEntitiesUpdating(session);
        doUnmanagedEntitiesUpdating(session);
        doEntitiesDetachingBeforeUpdating(session);
        doMultipleEntityOperationsInTransaction(session);

    }

    private void doEntitiesSelection(Session session)
    {
        List<Product> products = session.createQuery(
                "select p from Product p",
                Product.class).getResultList();

        out.printf("selected products:%n%s%n", products);
    }

    private void doEntitiesPersisting(Session session)
    {
        Product p1 = new Product(
                "Product1",
                "desc",
                true,
                new Money(new BigDecimal(45.56), "USD"));

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(p1);

        });

        Product p2 = fetchProductById(session, p1.getId());

        out.printf("created product:%n%s, equals against fetched:%b%n", p1, p1.equals(p2));
    }
    
    private void doEntitiesUpdating(Session session)
    {
        Product p1 = fetchAnyProduct(session);
    
        p1.setDescription("Updated description");
        p1.setPrice(new Money(BigDecimal.valueOf(2932.35454), "USD"));

        HibernateUtils.wrapInTransaction(session, () -> {});
        
        Product p2 = fetchProductById(session, p1.getId());

        out.printf("updated product:%n%s, equals against fetched:%b%n", p1, p1.equals(p2));
    }
    
    private void doUnmanagedEntitiesUpdating(Session session)
    {
        Product pivot = fetchAnyProduct(session);

        Product p1 = new Product(
                "UNMANAGED",
                "UNMANAGED",
                true,
                new Money(BigDecimal.valueOf(999), "EUR"));

        p1.setId(pivot.getId());

        HibernateUtils.wrapInTransaction(session, () -> session.merge(p1));

        out.printf("updated unmanaged after commit:%n%s%n", fetchProductById(session, p1.getId()));
    }

    private void doEntitiesDetachingBeforeUpdating(Session session)
    {
        Product p1 = fetchAnyProduct(session);

        printManagedEntityCount(session);
        printManagedEntities(session);

        session.detach(p1);

        printManagedEntityCount(session);

        p1.setName("Evicted Product");

        out.printf("detached product:%n%s%n", p1);

        HibernateUtils.wrapInTransaction(session, () -> {
        });

        out.printf("detached product after commit:%n%s%n", fetchProductById(session, p1.getId()));

        out.printf("attaching product:%n%s%n", p1);

        HibernateUtils.wrapInTransaction(session, () -> session.merge(p1));

        printManagedEntityCount(session);

        out.printf("attached product after commit:%n%s%n", fetchProductById(session, p1.getId()));

        printManagedEntityCount(session);
    }
    
    private void doMultipleEntityOperationsInTransaction(Session session)
    {
        Product newProduct = 
                new Product(
                        "New Product", "description",
                        true,
                        new Money(BigDecimal.valueOf(44), "USD")
                );
        
        Product p1 = fetchAnyProduct(session);

        out.printf("%nmultiple-op transaction started%n");

        HibernateUtils.wrapInTransaction(
            session, 
                () -> {
                    session.persist(newProduct);
                    session.remove(p1);
                }
        );

        out.printf(
            "new product after persisting:%n%s, product(%s) " + 
            "after removing not exists:%b%n",
            newProduct,
            p1.getId(),
            Objects.isNull(fetchProductById(session, p1.getId()))
        );
    }

    private Product fetchAnyProduct(Session session)
    {
        return fetchAnyEntity(session, Product.class);
    }
    
    private Product fetchProductById(Session session, Object id)
    {
        return fetchEntityById(session, id, Product.class);
    }

}
