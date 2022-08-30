package com.learning.hibernate.examples;

import org.hibernate.Session;

import com.learning.hibernate.entities.Meal;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Allergens;
import com.learning.hibernate.values.Money;

import static java.lang.System.out;

public class MappingEntityToMultipleTablesExample extends HibernateExample {

    public MappingEntityToMultipleTablesExample() {

        super("MappingEntityToMultipleTables");
     
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
      
        Meal meal =
            new Meal(
                "Meal1", 
                Money.ofUSD(56.78), 
                new Allergens(true, false, true)
            );

        out.printf("meal before persisting:%n%s%n", meal);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(meal);

        });

        Meal fetchedMeal = 
            session.createQuery(
                "select m from Meal m where id=" + meal.getId(), 
                Meal.class
                ).getSingleResult();
            
        out.printf("fetched persisted meal:%n%s%n", fetchedMeal);
    }
    
}
