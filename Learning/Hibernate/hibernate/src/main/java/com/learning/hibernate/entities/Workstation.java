package com.learning.hibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "workstations")
@SequenceGenerator(name = "pk_gen", sequenceName = "workstation_pk_gen", initialValue = 30)
public class Workstation extends SequencedExampleEntity {
    
    @NonNull
    private String name;
}
