package com.example.tacos.data.reactive.mongodb;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import com.example.tacos.domain.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoEntityConvertEventListener extends AbstractMongoEventListener {
    
    private MongoEntityIdSequenceService sequenceService;
    private Map<String, String> entitySequenceNames;

    @Autowired
    public MongoEntityConvertEventListener(
            MongoEntityIdSequenceService sequenceService,
            Map<String, String> entitySequenceNames
    )
    {
        this.sequenceService = sequenceService;
        this.entitySequenceNames = entitySequenceNames;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent event) {
        
        Object entity = event.getSource();

        String entitySequenceName = entitySequenceNames.get(entity.getClass().getSimpleName());

        if (Objects.isNull(entitySequenceName))
            return;

        Class<?> entityClass = entity.getClass();

        Long entityId = sequenceService.getNextValue(entitySequenceName);

        try
        {
            if((long)entityClass.getMethod("getId").invoke(entity) < 1)
                entity.getClass().getMethod("setId").invoke(entity, entityId);
        }

        catch (
            NoSuchMethodException | 
            IllegalAccessException | 
            InvocationTargetException ex)
        {
            
        }

    }
}
