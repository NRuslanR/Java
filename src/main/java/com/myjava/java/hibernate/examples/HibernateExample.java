/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.examples;

import com.myjava.java.ProgramExample;
import javax.persistence.*;
import java.util.*;
import javax.persistence.criteria.*;

/**
 *
 * @author ruslan
 */
public abstract class HibernateExample extends ProgramExample {
    
    protected class Pair<K, V>
    {
        private K first;
        private V second;
        
        public Pair(K first, V second)
        {
            this.first = first;
            this.second = second;
        }
        
        public K getFirst()
        {
            return first;
        }
        
        public V getSecond()
        {
            return second;
        }
    }
    
    protected EntityManager entityManager;
    protected boolean globalTransactionEnabled;
    
    public HibernateExample() {

        setGlobalTransactionEnabled(true);
    }
    
     private void createEntityManager(String persistenceUnitName) {
        
        EntityManagerFactory entityManagerFactory = 
                Persistence.createEntityManagerFactory(persistenceUnitName);
        
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    @Override
    protected void doBeforeRun()
    {
        if (globalTransactionEnabled)
            entityManager.getTransaction().begin();
    }
    
    @Override
    protected void doRun(Object... args)
    {
        createEntityManager((String)args[0]);
    }
    
    @Override
    protected void doAfterRun()
    {
        if (globalTransactionEnabled)
            entityManager.getTransaction().commit();
    }

    @Override
    protected void doOnFail(Exception ex) {
         
        if (globalTransactionEnabled)
            entityManager.getTransaction().rollback();
    }

    public boolean getGlobalTransactionEnabled()
    {
        return globalTransactionEnabled;
    }
    
    public void setGlobalTransactionEnabled(boolean globalTransactionEnabled)
    {
        this.globalTransactionEnabled = globalTransactionEnabled;
    }
    
    protected <T> void wrapInTransaction(Runnable operation)
    {
        entityManager.getTransaction().begin();
        
        try
        {
            operation.run();
            
            entityManager.getTransaction().commit();
        }
        
        catch (Exception ex)
        {
            entityManager.getTransaction().rollback();
            
            throw ex;
        }
    }
    
    protected <T> List<T> findByFieldsEquality(
            Class<T> entityClass, 
            Pair<String, Object>... fieldNameValues
    )
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<T> queryPattern = criteriaBuilder.createQuery(entityClass);
        
        Root<T> selection = queryPattern.from(entityClass);
        
        Predicate fieldEqualityPredicate = null;
        
        List<ParameterExpression<Object>> queryParameters = new ArrayList<>();
        
        for (Pair<String, Object> fieldNameValue : fieldNameValues)
        {
            ParameterExpression<Object> fieldParam =
                    criteriaBuilder.parameter(Object.class);
            
            queryParameters.add(fieldParam);
            
            Predicate fieldPredicate =
                criteriaBuilder.equal(
                        selection.get(fieldNameValue.getFirst()), 
                        fieldParam
                );
            
            fieldEqualityPredicate =
                    fieldEqualityPredicate == null ?
                    fieldPredicate :
                    criteriaBuilder.and(fieldEqualityPredicate, fieldPredicate);
                    
        }    
        
        queryPattern = queryPattern.select(selection);
                
        if (fieldEqualityPredicate != null)
            queryPattern = queryPattern.where(fieldEqualityPredicate);
        
        TypedQuery<T> typedQuery = entityManager.createQuery(queryPattern);
        
        if (fieldEqualityPredicate != null)
        {
            for (int i = 0; i < queryParameters.size(); ++i)
            {
                typedQuery.setParameter(
                        queryParameters.get(i), 
                        fieldNameValues[i].getSecond()
                );
            }
        }
        
        return typedQuery.getResultList();
    }

}
