package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "oaks")
@DiscriminatorValue("oak")
public class Oak extends Tree {
    
    public Oak(String name, int branchCount, double averageAcornSize)
    {
        super(name, branchCount);
        setAverageAcornSize(averageAcornSize);
    }

    @Column(name = "avg_acorn_size")
    private double averageAcornSize;

}
