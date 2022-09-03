package com.learning.hibernate.entities;

import java.util.Objects;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractExampleEntity<IdType> {
    
    public boolean equals(Object other)
    {
        return !Objects.isNull(other) &&
                getClass().equals(other.getClass()) &&
                equals((AbstractExampleEntity) other);
    }

    protected boolean equals(AbstractExampleEntity other)
    {
        return getId().equals(other.getId());
    }

    public abstract IdType getId();
    
}
