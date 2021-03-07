/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.examples;

import com.myjava.java.ProgramExample;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author ruslan
 */
public abstract class HibernateSessionExample extends ProgramExample {
    
    protected SessionFactory sessionFactory;
    protected Session session;
    
    public HibernateSessionExample()
    {
        StandardServiceRegistry ssr = 
            new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    
    @Override
    protected void doBeforeRun()
    {
        session = sessionFactory.openSession();
    }

    @Override
    protected void doOnClean()
    {
        session.close();
    }
    
    protected void wrapInTransaction(Runnable operation) throws Exception
    {
        Transaction transaction = session.beginTransaction();
        
        try
        {
            operation.run();
            
            transaction.commit();
        }
        
        catch (Exception ex)
        {
            transaction.rollback();
            
            throw ex;
        }
    }
}
