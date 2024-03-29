package com.learning.hibernate.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.apache.commons.lang3.RandomUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.service.ServiceRegistry;
import org.postgresql.ds.PGPoolingDataSource;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

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
        
        if (RandomUtils.nextInt(0, 100) > 5)
            doJPAInitialization();

        else doHibernateInitialization();
    }

    private static void doJPAInitialization()
    {
        EntityManagerFactory entityManagerFactory =
            Persistence
                .createEntityManagerFactory(
                    "hibernate-learning", 
                    Map.of(Environment.DATASOURCE, getDataSource())
                );

        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    private static void doHibernateInitialization()
    {
        serviceRegistry = 
                new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .applySetting(Environment.DATASOURCE, getDataSource())
                    .build();
        
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

    private static DataSource getDataSource()
    {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        dataSource.setURL("jdbc:postgresql://localhost:5432/hibernate_learning");
        dataSource.setUser("hibernate_user");
        dataSource.setPassword("123456");

        return ProxyDataSourceBuilder
                .create(dataSource)
                .asJson()
                .countQuery()
                .logQueryToSysOut()
                .multiline()
                .build();
    }
    
    public static void shutdown()
    {
        if (!Objects.isNull(sessionFactory))
            sessionFactory.close();

        if (!Objects.isNull(serviceRegistry)) 
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    public static Session newSession() throws Exception
    {
        return getSessionFactory().openSession();
    }
    
    public static int getManagedEntityCount(Session session)
    {
        return getPersistenceContext(session).getNumberOfManagedEntities();
    }

    public static Map.Entry<Object,EntityEntry>[] getManagedEntities(Session session)
    {
        return getPersistenceContext(session).reentrantSafeEntityEntries();
    }

    public static PersistenceContext getPersistenceContext(Session session)
    {
        return ((SessionImplementor) session).getPersistenceContext();
    }
    
    public static void persistInTransaction(Session session, Object... objects)
    {
        persistInTransaction(session, Arrays.asList(objects));
    }

    public static <T> void persistInTransaction(Session session, Collection<T> objects)
    {
        for (Object object : objects)
            session.persist(object);

        runFlushedTransaction(session);
    }

    public static void runFlushedTransaction(Session session)
    {
        wrapInTransaction(session, () -> {
        });
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
