package com.example.tacos.data.reactive.mongodb;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Document(collection = "database_sequences")
public class MongoEntityIdSequence {

    @Id
    @Setter(value = AccessLevel.NONE)
    private String id;

    @Setter(AccessLevel.NONE)
    private long value;
    
}
