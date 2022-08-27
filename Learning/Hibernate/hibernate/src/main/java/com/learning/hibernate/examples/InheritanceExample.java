package com.learning.hibernate.examples;

import org.hibernate.Session;

import com.learning.hibernate.entities.Batman;
import com.learning.hibernate.entities.Oak;
import com.learning.hibernate.entities.SpiderMan;
import com.learning.hibernate.entities.Spruce;
import com.learning.hibernate.entities.SuperHero;
import com.learning.hibernate.entities.Tree;
import com.learning.hibernate.entities.Spruce.ConeType;
import com.learning.hibernate.utils.HibernateUtils;

import static java.lang.System.out;

public class InheritanceExample extends HibernateExample {

    public InheritanceExample() {

        super("Inheritance");

    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        Oak oak =  new Oak("Oak1", 45, 98.453);       
        Spruce spruce = new Spruce("Spruce1", 65, ConeType.Green);
        
        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(oak);
            session.persist(spruce);

        });

        out.printf(
            "fetched persisted oak:%n%s%n", 
            fetchEntityById(session, oak.getId(), Oak.class)
        );

        out.printf(
            "fetched persisted spruce:%n%s%n",
            fetchEntityById(session, spruce.getId(), Spruce.class)
        );

        out.printf(
            "fetched any spurce for select query show:%n%s%n",
            fetchAnyEntity(session, Spruce.class)
        );

        out.printf(
            "fetched all trees: %n%s%n",
            fetchAllEntities(session, Tree.class)
        );

        SpiderMan spiderMan = new SpiderMan("Spider Man 1", 45.3243);
        Batman batman = new Batman("Batman1", true);

        HibernateUtils.wrapInTransaction(session, () -> {

            session.persist(spiderMan);
            session.persist(batman);

        });

        out.printf("persisted spider-man:%n%s%n", spiderMan);
        out.printf("persisted batman:%n%s%n", batman);

        out.printf(
            "fetched any batman for select query show:%n%s%n",
            fetchAnyEntity(session, Batman.class)
        );

        out.printf(
            "fetched all superheroes:%n%s%n",
            fetchAllEntities(session, SuperHero.class)
        );
    }
    
}
