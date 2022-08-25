package com.learning.hibernate.examples;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.learning.hibernate.entities.Address;
import com.learning.hibernate.entities.Employee;
import com.learning.hibernate.entities.OrderItem;
import com.learning.hibernate.entities.Person;
import com.learning.hibernate.entities.Product;
import com.learning.hibernate.entities.Workstation;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;
import com.learning.hibernate.values.PersonName;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OneToOneExample extends HibernateExample {

    public OneToOneExample() {
        
        super("OneToOne");

    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {

        runBidirectionalOneToOneExample(session);
        runUnidirectionalOneToOneExample(session);
        runOptionalOneToOneWithJoinTableExample(session);

    }

    private void runBidirectionalOneToOneExample(Session session) {

        out.println("-------BIDIRECTIONAL ONE-TO-ONE RELATIONSHIP" +
                "WITH DIFFERENT PRIMARY KEY COLUMNS-----------"
        );

        Person person = new Person(
                PersonName.of("Michael", "Rupert"),
                new Address("New York", "Bird Fly", "109", "22A"));

        out.printf("entity before flushed:%n%s%n", person);

        printManagedEntityCount(session);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(person);

        });

        out.printf("entity after flushed:%n%s%n", person);

        printManagedEntityCount(session);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.remove(person);

        });

        printManagedEntityCount(session);

        out.printf(
                "address not exists after cascade removing: %b%n",
                Objects.isNull(
                        fetchEntityById(
                                session, person.getAddress().getId(), Address.class)));
    }

    private void runUnidirectionalOneToOneExample(Session session) {

        out.println("---------UNIDIRECTIONAL ONE-TO-ONE RELATIONSHIP" +
                "WITH SHARED PRIMARY KEY COLUMN--------"
        );

        List<OrderItem> orderItems =
            Arrays.asList(
                new OrderItem(
                    new Product(
                        "P1", 
                        "D1", 
                        true, 
                        Money.ofUSD(56.78)
                    ), 
                    10
                ),
                new OrderItem(
                    new Product(
                        "P2", 
                        "D2", 
                        false, 
                        Money.ofEUR(117)
                    ), 
                    5
                ),
                new OrderItem(
                    new Product(
                        "P3", 
                        "D3", 
                        true, 
                        Money.ofUSD(13.259853)
                    ), 
                    3
                )
                );
            
        out.printf("order items before flushed: %n%s%n", orderItems);

        HibernateUtils.wrapInTransaction(session, () -> {
            
            orderItems.forEach((i) -> session.persist(i));

        });

        printManagedEntityCount(session);

        out.printf("order items after flushed: %n%s%n", orderItems);

        out.printf("fetched order item count: %d%n",
            fetchAllEntities(session, OrderItem.class).size()
        );

        HibernateUtils.wrapInTransaction(session, () -> {

            orderItems.forEach((i) -> session.remove(i));

        });

        printManagedEntityCount(session);

        out.printf("products after order items removing: %n%s%n",
            session.createQuery(
                String.format(
                    "select p from Product p where id in (%s)",
                    StringUtils.join(
                        orderItems.stream()
                        .map(OrderItem::getProduct)
                        .map(Product::getId)
                        .collect(Collectors.toList()), 
                        ","
                    )
                ),
                Product.class
            ).getResultList()
        );
    }

    private void runOptionalOneToOneWithJoinTableExample(Session session) {

        out.println("-----ONE-TO-ONE RELATIONSHIP WITH JOIN TABLE----------");

        Employee employee = 
            new Employee(
                PersonName.of("Robert", "Rompel"), 
                new Address("Los Angeles", "Avenu", "Ut-32", "2032"), 
                new Workstation("UMBRA")
            );

        out.printf("employee before persisting:%n%s%n", employee);

        HibernateUtils.wrapInTransaction(session, () -> {
            session.persist(employee);
        });

        out.printf("employee after persisting:%n%s%n", employee);

        printManagedEntityCount(session);

        employee.setName(PersonName.of("Robert", "Nash"));
        employee.getWorkstation().setName("UMBRA UPD");

        HibernateUtils.runFlushedTransaction(session);

        out.printf(
            "fetched employee after merging:%n%s%n",
            fetchEntityById(session, employee.getId(), Employee.class)
        );

        HibernateUtils.wrapInTransaction(session, () -> {

            session.remove(employee);

        });

        out.printf(
            "employee is not existing after removing:%b%n", 
            Objects.isNull(
                fetchEntityById(session, employee.getId(), Employee.class)
            )
        );

        out.printf("workstation after employee removing:%n%s%n",
            fetchEntityById(
                session,
                employee.getWorkstation().getId(),
                Workstation.class
            )
        );

        printManagedEntityCount(session);

    }
    
}
