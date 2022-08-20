package com.learning.hibernate.utils;

import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.service.ServiceRegistry;

import jakarta.persistence.EntityTransaction;

public class HibernateUtils {
    
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() throws Exception
    {
        Initialize();

        return sessionFactory;  
    }
    
    public static void Initialize()
    {
        if (!Objects.isNull(sessionFactory))
            return;

        serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);

            Metadata metadata = metadataSources.buildMetadata();

            sessionFactory = metadata.buildSessionFactory();
        }

        catch (Exception ex) {

            StandardServiceRegistryBuilder.destroy(serviceRegistry);

            throw ex;
        }
    }

    public static void shutdown()
    {
        if (!Objects.isNull(serviceRegistry)) {
            sessionFactory.close();

            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static Session newSession() throws Exception
    {
        return getSessionFactory().openSession();
    }
    
    public static int getManagedEntityCount(Session session)
    {
        SessionImplementor sessionImplementor = (SessionImplementor) session;

        return sessionImplementor.getPersistenceContext().getNumberOfManagedEntities();
    }

    public static Map.Entry<Object,EntityEntry>[] getManagedEntities(Session session)
    {
        SessionImplementor sessionImplementor = (SessionImplementor)session;

        return sessionImplementor.getPersistenceContext().reentrantSafeEntityEntries();
    }

    public static void wrapInTransaction(Session session, Runnable operation)
    {
        EntityTransaction transaction = session.beginTransaction();

        try
        {
            operation.run();

            transaction.commit();
        }

        catch(Exception ex)
        {
            transaction.rollback();

            throw ex;
        }
    }
}
