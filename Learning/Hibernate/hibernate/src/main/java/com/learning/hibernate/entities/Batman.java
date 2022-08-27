package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "batmans")
@SequenceGenerator(name = "pk_gen", sequenceName = "super_hero_pk_gen", initialValue = 20)
public class Batman extends SuperHero {
    
    public Batman(String name, boolean hasCloak)
    {
        super(name);
        setHasCloak(hasCloak);
    }
    
    @Column(name = "has_cloak")
    private boolean hasCloak;
}
