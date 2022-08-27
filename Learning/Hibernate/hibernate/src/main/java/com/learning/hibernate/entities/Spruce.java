package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "spruces")
@DiscriminatorValue("spruce")
public class Spruce extends Tree {
    
    public enum ConeType { Green, Red }

    public Spruce(String name, int branchCount, ConeType coneType)
    {
        super(name, branchCount);
        setConeType(coneType);
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "cone_type")
    private ConeType coneType;

}
