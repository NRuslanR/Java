package com.learning.hibernate.examples;

import org.hibernate.Session;

import com.learning.hibernate.entities.DummyEntity;
import com.learning.hibernate.utils.HibernateUtils;

import static com.learning.hibernate.utils.HibernateUtils.wrapInTransaction;

public class EntityLifeCycleEventsExample extends HibernateExample {

    public EntityLifeCycleEventsExample()
    {
        super("EntityLifeCycleEvents");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        wrapInTransaction(session, () -> {

            session.persist(new DummyEntity(1));

        });

        wrapInTransaction(session, () -> {

            DummyEntity entity = fetchAnyEntity(session, DummyEntity.class);

            entity.setValue(10);

        });
        
        wrapInTransaction(session, () -> {

            DummyEntity entity = fetchAnyEntity(session, DummyEntity.class);

            session.remove(entity);

        });

    }
    
}
