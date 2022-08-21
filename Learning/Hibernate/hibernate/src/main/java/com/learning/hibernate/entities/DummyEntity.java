package com.learning.hibernate.entities;

import java.util.logging.Logger;

import com.learning.hibernate.entities.listeners.DummyEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "dummies")
@ToString(callSuper = true)
@EntityListeners(value = { DummyEntityListener.class })
public class DummyEntity extends ExampleEntity {
    
    @Getter(value = AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    Logger logger = Logger.getLogger(DummyEntity.class.getName());

    @NonNull
    private long value;

    @PrePersist
    public void onPersisting()
    {
        logger.info(toString() + " persisting");
    }

    @PostPersist
    public void onPersisted()
    {
        logger.info(toString() + " persisted");
    }

    @PreUpdate
    public void onUpdating()
    {
        logger.info(toString() + " updating");
    }

    @PostUpdate
    public void onUpdated()
    {
        logger.info(toString() + " updated");
    }

    @PreRemove
    public void onRemoving()
    {
        logger.info(toString() + " removing");
    }

    @PostRemove
    public void onRemoved()
    {
        logger.info(toString() + " removed");
    }

    @PostLoad
    public void onLoaded()
    {
        logger.info(toString() + " loaded");
    }
}
