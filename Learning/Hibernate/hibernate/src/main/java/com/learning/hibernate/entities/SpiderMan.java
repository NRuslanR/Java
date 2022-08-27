package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "pk_gen", sequenceName = "super_hero_pk_gen", initialValue = 20)
public class SpiderMan extends SuperHero {
    
    public SpiderMan(String name, double webCapacity)
    {
        super(name);
        setWebCapacity(webCapacity);
    }

    @Column(name = "web_capacity")
    private double webCapacity;
}
