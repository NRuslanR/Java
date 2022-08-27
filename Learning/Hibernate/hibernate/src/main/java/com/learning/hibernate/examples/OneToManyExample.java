package com.learning.hibernate.examples;

import static java.lang.System.out;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.learning.hibernate.entities.Address;
import com.learning.hibernate.entities.CategorizedProduct;
import com.learning.hibernate.entities.Customer;
import com.learning.hibernate.entities.Order;
import com.learning.hibernate.entities.OrderItem;
import com.learning.hibernate.entities.Product;
import com.learning.hibernate.entities.ProductCategory;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;
import com.learning.hibernate.values.PersonName;

public class OneToManyExample extends HibernateExample {

    public OneToManyExample() {

        super("OneToMany");
       
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        runBidirectionalOneToManyExample(session);
        runUnidirectionalOneToMany(session);
        
    }

    private void runBidirectionalOneToManyExample(Session session) {

        out.println("------BIDIRECTIONAL ONE-TO-MANY RELATIONSHIP-------");

        Customer customer =
            new Customer(
                PersonName.of("Dmitry", "Punisher"), 
                new Address("Kazan", "Lobachevsky", "34", "819"), 
                Arrays.asList(
                    new Order(
                        "Order1", 
                        Arrays.asList(
                            new OrderItem(
                                new Product(
                                    "P1", "D1", 
                                    true, Money.ofUSD(24.988764)
                                ), 
                                6
                            ),
                            new OrderItem(
                                new Product(
                                    "P2", "D2", 
                                    false, Money.ofEUR(78.1873)
                                ), 
                                3
                            )
                        ),
                        Date.from(
                            LocalDateTime.of(2021, Month.AUGUST, 13, 14, 56)
                            .atZone(ZoneId.systemDefault()).toInstant()
                        ) 
                    ),
                    new Order(
                        "Order2", 
                        Arrays.asList(
                            new OrderItem(
                                new Product(
                                    "P3", "description", 
                                    true, Money.ofUSD(44.89)
                                ),
                                19
                            ),
                            new OrderItem(
                                new Product(
                                    "P4", "d4", 
                                    false, Money.ofEUR(56)
                                ), 
                                4
                            )
                        ), 
                        Date.from(
                            LocalDateTime.of(2022, Month.FEBRUARY, 13, 22, 13)
                            .atZone(ZoneId.systemDefault()).toInstant()
                        )
                    )
                )
            );
            
        out.printf("customer before persisting:%n%s%n", customer);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(customer);

        });
 
        out.printf(
            "fetched customer after persisting:%n%s%n", 
            fetchEntityById(session, customer.getId(), Customer.class)
        );

        printManagedEntityCount(session);

        session.detach(customer);

        out.printf("managed entity count after customer detaching:%d%n",
            HibernateUtils.getManagedEntityCount(session)
        );

        Order someOrder = customer.getOrders().stream().findAny().get();

        out.printf("Was customer detached ?:%b%n", !session.contains(customer));

        out.printf("Was detached customer's order detached ?:%b%n",
                !session.contains(someOrder));

        out.printf("Was detached customer's order item detached?: %b%n", 
                !session.contains(someOrder.getOrderItems().stream().findAny().get()));
            
        Customer mergedCustomer = session.merge(customer);

        someOrder = fetchEntityById(session, someOrder.getId(), Order.class);

        out.printf("fetched customer order:%n%s%n", someOrder);
        out.printf("fetched order's customer:%n%s%n", someOrder.getCustomer());

        HibernateUtils.wrapInTransaction(session, () -> {

            session.remove(mergedCustomer);

        });

        List<Order> removedCustomerOrder =
            session.createQuery(
                "select o from Order o " +
                "join o.customer c where c.id = " +
                mergedCustomer.getId(),
                Order.class
            ).getResultList();

        out.printf(
            "Is there an customer after removing ?: %b, " +
            "and are there any of his orders ?:%b%n%s%n",
            fetchEntityById(session, mergedCustomer.getId(), Customer.class),
            !removedCustomerOrder.isEmpty(),
            StringUtils.join(removedCustomerOrder, "%n")
        );
    }
    
    private void runUnidirectionalOneToMany(Session session) {

        out.println("-----UNIDIRECTIONAL ONE-TO-MANY RELATIONSHIP---------");

        ProductCategory category =
            new ProductCategory(
                "Technics", 
                Arrays.asList(
                    new CategorizedProduct(
                        "Printer"
                    ),
                    new CategorizedProduct(
                        "Monitor"
                    ),
                    new CategorizedProduct(
                        "Laptop"
                    )
                )
            );
            
        out.printf("product category before persisting:%n%s%n", category);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(category);

        });

        out.printf("product category after persisting:%n%s%n", category);

        out.printf("some product of category:%n%s%n",
            fetchEntityById(
                session, 
                category.getProducts().stream().findAny().get().getId(), 
                CategorizedProduct.class
            )
        );

        printManagedEntityCount(session);

        session.detach(category);

        out.printf(
            "Were products detached after theirs category was detached ?:%b%n",
            !category.getProducts().stream().allMatch(session::contains)
        );

        printManagedEntityCount(session);

        ProductCategory mergedCategory = session.merge(category);

        out.printf(
            "Were products merged after theirs category was merged ?:%b%n",
            mergedCategory.getProducts().stream().allMatch(session::contains)
        );

        HibernateUtils.wrapInTransaction(session, () -> {

            session.remove(mergedCategory);

        });

        out.printf(
            "Is there an product category after removing ?:%b, " +
            "and are there any of it products ?:%b%n",
            fetchEntityById(
                session,
                mergedCategory.getId(),
                ProductCategory.class
            ),
            !session.createQuery(
                "select p from Product p " +
                "join ProductCategory pc where pc.id=" +
                mergedCategory.getId(),
                ProductCategory.class
            ).getResultList().isEmpty()
        );

    }
    
}
