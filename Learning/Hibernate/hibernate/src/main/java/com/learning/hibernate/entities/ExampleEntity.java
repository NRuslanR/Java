package com.learning.hibernate.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class ExampleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    
    public boolean equals(Object other)
    {
        return 
        !Objects.isNull(other) && 
            getClass().equals(other.getClass()) && 
                equals((ExampleEntity) other);
    }

    protected boolean equals(ExampleEntity other)
    {
        return getId() == other.getId();
    }
}
