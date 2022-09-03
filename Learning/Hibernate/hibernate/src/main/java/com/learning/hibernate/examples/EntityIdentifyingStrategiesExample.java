package com.learning.hibernate.examples;

import static java.lang.System.out;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import org.hibernate.Session;

import com.learning.hibernate.entities.Address;
import com.learning.hibernate.entities.Book;
import com.learning.hibernate.entities.Meal;
import com.learning.hibernate.entities.Person;
import com.learning.hibernate.entities.SpiderMan;
import com.learning.hibernate.entities.Spruce;
import com.learning.hibernate.entities.Spruce.ConeType;
import com.learning.hibernate.entities.Workstation;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Allergens;
import com.learning.hibernate.values.BookId;
import com.learning.hibernate.values.Money;
import com.learning.hibernate.values.PersonName;

public class EntityIdentifyingStrategiesExample extends HibernateExample {

    public EntityIdentifyingStrategiesExample() {

        super("EntityIdentifyingStrategies");
       
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
    
        SpiderMan spiderMan = new SpiderMan("Spider Man 1", 56.54);
        
        Person person = 
            new Person(
                PersonName.of("Ivan", "Ivanov"), 
                new Address("Moscow", "Goncharova", "134", "45B")
            );

        Workstation workstation = new Workstation("Workstation1");

        Spruce spruce = new Spruce("Spruce 1", 34, ConeType.Green);

        Meal meal = 
            new Meal(
                "Meal 1", 
                Money.ofEUR(45), 
                new Allergens(true, false, true)
            );

        Book book = 
            new Book(
                BookId.of("James Conan", "Introduction To Being"), 
                ZonedDateTime.of(
                    LocalDateTime.of(
                        LocalDate.of(1975, Month.DECEMBER, 12), 
                        LocalTime.of(15, 45, 27)
                    ), 
                    ZoneId.systemDefault()
                )
            );
        
        HibernateUtils.persistInTransaction(
            session, 
            Arrays.asList(spiderMan, person, workstation, spruce, meal, book)
        );

        Arrays
            .asList(spiderMan, person, workstation, spruce, meal, book)
            .forEach(e ->
                printEntityAfterPersistAndAfterFlushThen(session, e)
            );
    }

    private void printEntityAfterPersistAndAfterFlushThen(Session session, Object entity)
    {
        session.persist(entity);

        out.printf("%s after persist:%n%s%n", entity.getClass().getSimpleName(), entity);

        HibernateUtils.runFlushedTransaction(session);

        out.printf(
            "%s after flush:%n%s%n", entity.getClass().getSimpleName(),
            fetchAnyEntity(session, entity.getClass())
        );
    }
}
