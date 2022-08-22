package com.learning.hibernate.examples;

import org.hibernate.Session;

import com.learning.hibernate.entities.Person;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.PersonName;

import static java.lang.System.out;

public class AttributeConvertersExample extends HibernateExample {

    public AttributeConvertersExample() {
        
        super("AttributeConverters");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        Person person = new Person(PersonName.of("David", "Hume"));

        out.printf("entity to be persisted:%n%s%n", person);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(person);

        });
        
        out.printf("persisted entity:%n%s%n", person);

        Person fetchedPerson = 
            fetchEntityById(session, person.getId(), Person.class);
        
        out.printf("fetched entity:%n%s%n", person);
        out.printf(
            "original and fetched entities are equals:%b%n",
            fetchedPerson.equals(person)
        );

        out.printf(
            "person names of the original and fetched entities are equals:%b%n",
                person.getName().equals(fetchedPerson.getName())
        );
    }
    
}
