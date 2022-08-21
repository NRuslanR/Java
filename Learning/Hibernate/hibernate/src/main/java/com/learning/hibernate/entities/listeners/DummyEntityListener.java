package com.learning.hibernate.entities.listeners;

import java.util.logging.Logger;

import com.learning.hibernate.entities.DummyEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class DummyEntityListener {
    
    private Logger logger = Logger.getLogger(DummyEntityListener.class.getName());

    @PrePersist
    @PreUpdate
    @PreRemove
    public void onDummyModifying(DummyEntity entity)
    {
        if (entity.getId() == 0)
            logger.info("Dummy ON ADDING");

        else
            logger.info(String.format("Dummy(%s) ON UPDATING or REMOVING", entity.getId()));
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void onDummyModified(DummyEntity entity)
    {
        logger.info(String.format("Dummy(%s) ON MODIFIED", entity.getId()));
    }

    @PostLoad
    public void onDummyLoaded(DummyEntity entity)
    {
        logger.info(String.format("Dummy(%s) ON LOADED", entity.getId()));
    }
}
