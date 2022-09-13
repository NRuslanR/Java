package com.learning.hibernate.examples;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.learning.hibernate.entities.Address;
import com.learning.hibernate.entities.Customer;
import com.learning.hibernate.entities.Order;
import com.learning.hibernate.entities.OrderItem;
import com.learning.hibernate.entities.Product;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;
import com.learning.hibernate.values.PersonName;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class JPAQueryTypesExample extends HibernateExample {

    private static final Date ordersSearchBySameCreationDate =
        Date.from(
            LocalDate.of(2020, Month.APRIL, 13)
                .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
        );

    public JPAQueryTypesExample() {
        super("JPAQueryTypes");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {

        prepareData(session);
        runQueries(session);
    }

    private void prepareData(Session session)
    {
        List<Order> orderList1 = Arrays.asList(
            new Order(
                "Order1",
                Arrays.asList(
                    new OrderItem(
                        new Product(
                            "P1", "",
                            true, Money.ofEUR(
                            23)
                        ),
                        5
                    )
                ),
                ordersSearchBySameCreationDate
            ),
            new Order(
                "Order2",
                Arrays.asList(
                    new OrderItem(
                        new Product(
                            "P2", "fwefwe",
                            true, Money.ofEUR(943.3232)
                        ),
                        15
                    )
                ),
                ordersSearchBySameCreationDate
            )
        );

        List<Order> orderList2 = Arrays.asList(
                new Order(
                    "Order3",
                    Arrays.asList(
                        new OrderItem(
                            new Product(
                                "P3", "efe",
                                true, Money.ofUSD(123)
                            ),
                            2
                        )
                    ),
                    ordersSearchBySameCreationDate
                ),
                new Order(
                    "Order4",
                    Arrays.asList(
                        new OrderItem(
                            new Product(
                                "P4", "efe",
                                true, Money.ofUSD(13)
                            ),
                            33
                        )
                    ),
                    Date.from(
                        LocalDate.now()
                            .atStartOfDay(ZoneId.systemDefault()).toInstant())
                )
            );

        Customer 
            customer1 = 
                new Customer(
                    PersonName.of("Ravel", "Ichitse"),
                    new Address("city", "street", "home", "room"),
                    orderList1
                ),
            customer2 = 
                new Customer(
                    PersonName.ofFullName("Frank Ralf"),
                    new Address("city", "street", "home", "room"),
                    orderList2
                );

        HibernateUtils.persistInTransaction(session, customer1, customer2);
    }
    
    private void runQueries(Session session) {

        runNamedQueries(session);
        runNativeQueries(session);
        runHibernateQueries(session);
        runCriteriaQueries(session);
    }

    private void runNamedQueries(Session session) {

        List<Order> ordersAtSameCreationDate =
            session.createNamedQuery(
                Order.FIND_ALL_ORDERS_AT_CREATION_DATE, Order.class
            ).setParameter(1, ordersSearchBySameCreationDate)
                        .getResultList();
                    
        printEntities(ordersAtSameCreationDate, "orders at same creation date");

        List<Order> ordersOfSameCustomer =
            session.createNamedQuery(
                Order.FIND_ALL_ORDERS_BY_CUSTOMER,
                Order.class
            ).setParameter("id", 2)
            .getResultList();
        
        printEntities(ordersOfSameCustomer, "orders of same customer");
    }

    private void runNativeQueries(Session session) {

        List<Order> orders =
            session.createNativeQuery(
                "select o.* from orders o " +
                "left join customers c on c.person_id = o.customer_id " +
                "join persons p on p.person_id = c.person_id " +
                "where p.name ~* :name", Order.class
            ).setParameter("name", "I")
                        .getResultList();
                    
        printEntities(orders, 
        "orders by native query of customer with name starting with 'Ra'"
        );

    }

    private void runHibernateQueries(Session session) {

        List<Order> orders =
            session.createSelectionQuery(
                "from Order o " +
                "join o.customer c where c.id = ?1", Order.class
            ).setParameter(1, 1)
                        .getResultList();
                    
        printEntities(orders, "entities fetched by HQL for customer id = 1");
    }

    private void runCriteriaQueries(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> selectOrders = cq.from(Order.class);
        Join<Order, Customer> joinCustomers = selectOrders.join("customer");
        
        CriteriaQuery<Order> targetQuery =
            cq
            .select(selectOrders)
                .where(
                    cb.and(
                        cb.like(selectOrders.get("name"), "%er4"), 
                        cb.equal(
                            joinCustomers.get("address").get("city"), 
                            cb.parameter(String.class, "city")
                        )
                    )
                );
            
        List<Order> orders =
            session
                .createQuery(targetQuery)
                .setParameter("city", "city")
                .getResultList();

        printEntities(orders, "orders fetched by Criteria API for customer id = 2 and Order4");
    }
    
}
