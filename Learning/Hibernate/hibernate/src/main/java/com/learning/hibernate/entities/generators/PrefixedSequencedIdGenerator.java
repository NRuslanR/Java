package com.learning.hibernate.entities.generators;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class PrefixedSequencedIdGenerator implements IdentifierGenerator {

    private String prefix;

    @Override
    public Object generate(
        SharedSessionContractImplementor session,
        Object object
    ) throws HibernateException {
        
        EntityPersister entityPersister = 
            session.getEntityPersister(
                object.getClass().getName(),
                object
            );

        String query = 
            String.format("select %s from %s", 
                entityPersister.getIdentifierPropertyName(),
                object.getClass().getSimpleName()
            );
        
        long currentId =
            session
                .createQuery(query, String.class)
                .stream()
                .map(id -> id.replace(prefix + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0);
                
        return prefix + "-" + (currentId + 1); 
    }

    @Override
    public void configure(
            Type type,
            Properties params,
            ServiceRegistry serviceRegistry
    ) throws MappingException {
      
        IdentifierGenerator.super.configure(type, params, serviceRegistry);

        prefix = params.getProperty("prefix");
    }
    
}
