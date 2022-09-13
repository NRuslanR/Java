package com.learning.hibernate.examples;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.learning.examples.AbstractExample;

import com.learning.hibernate.utils.HibernateUtils;

public abstract class HibernateExample extends AbstractExample {

    protected HibernateExample(String name)
    {
        super(name);
    }

    @Override
    public void run(Object data) {

        try (Session session = HibernateUtils.newSession()) {
            
            doRun(session, data);
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    protected abstract void doRun(Session session, Object data) throws Exception; 
    
    protected void printManagedEntityCount(Session session)
    {
        out.printf(
            "managed entity count:%d%n",
            HibernateUtils.getManagedEntityCount(session)   
        );
    }

    protected void printManagedEntities(Session session)
    {
        out.printf(
            "managed entities:%n%s%n",
            StringUtils.join(
                Arrays.stream(HibernateUtils.getManagedEntities(session))
                    .map(entry -> String.format(
                            "{%nentity:%n%s%n,%nentry:%n%s%n}",
                            entry.getKey(), entry
                                    .getValue())
                    )
                    .toList(),
                    "%n"
            )
        );
    }
    
    protected <T> T fetchAnyEntity(Session session, Class<T> type)
    {
        return session.createQuery(
                String.format("select e from %s e", type.getSimpleName()),
                type)
                .setMaxResults(1)
                .getSingleResult();
    }
    
    protected <T> List<T> fetchAllEntities(Session session, Class<T> type)
    {
        return 
            session.createQuery(
                String.format(
                    "select e from %s e",
                    type.getName()
                ),
                type
            )
            .getResultList();
    }
    
    protected <T> T fetchEntityById(Session session, Object id, Class<T> type)
    {
        return session.get(type, id);
    }

    protected <T> void printEntities(Iterable<T> entities)
    {
        printEntities(entities, "");
    }

    protected <T> void printEntities(Iterable<T> entities, String title)
    {
        String entityRowsString = 
            StringUtils.join(entities, System.getProperty("line.separator"));

        String output =
            !title.isEmpty() ? 
                String.format("%s:%n%s%n", title, entityRowsString) :
                String.format("%n%s%n", entityRowsString);

        out.print(output);
    }
}
