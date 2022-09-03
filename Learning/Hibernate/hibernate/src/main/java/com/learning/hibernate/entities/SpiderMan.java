package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString(callSuper = true)
@Entity
@Table(name = "spider_mans")
public class SpiderMan extends SuperHero {
    
    public SpiderMan(String name, double webCapacity)
    {
        super(name);
        setWebCapacity(webCapacity);
    }

    @Column(name = "web_capacity")
    private double webCapacity;
}
