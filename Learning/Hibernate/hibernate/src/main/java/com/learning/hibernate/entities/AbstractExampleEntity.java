package com.learning.hibernate.entities;

import java.util.Objects;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractExampleEntity {
    
    public boolean equals(Object other)
    {
        return !Objects.isNull(other) &&
                getClass().equals(other.getClass()) &&
                equals((AbstractExampleEntity) other);
    }

    protected boolean equals(AbstractExampleEntity other)
    {
        return getId() == other.getId();
    }

    public abstract long getId();
    
}
