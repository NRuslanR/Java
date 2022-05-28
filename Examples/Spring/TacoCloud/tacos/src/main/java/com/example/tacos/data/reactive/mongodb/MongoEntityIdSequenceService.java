package com.example.tacos.data.reactive.mongodb;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsObject.Options;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MongoEntityIdSequenceService {

    private ReactiveMongoOperations mongoOperations;

    @Autowired
    public MongoEntityIdSequenceService(ReactiveMongoOperations mongoOperations)
    {
        this.mongoOperations = mongoOperations;

    }

    public Long getNextValue(String entitySequenceName)
    {
        try
        {
            MongoEntityIdSequence entityIdSequence =
                mongoOperations.findAndModify(
                    query(where("_id").is(entitySequenceName)), 
                    new Update().inc("value", 1), 
                    FindAndModifyOptions
                        .options()
                        .returnNew(true)
                        .upsert(true), 
                        MongoEntityIdSequence.class
                ).block();

            return !Objects.isNull(entityIdSequence) ? entityIdSequence.getValue() : 1;
        }

        catch(Exception ex)
        {
            log.info(ex.getMessage());

            throw ex;
        }
    }
    
}
