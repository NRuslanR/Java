package com.learning.hibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString(callSuper = true)
@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "pk_gen", sequenceName = "address_pk_gen", initialValue = 100, allocationSize = 2)
public class Address extends SequencedExampleEntity {

    @NonNull
    private String city;

    @NonNull
    private String street;

    @NonNull
    private String home;

    @NonNull
    private String room;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Person person;
    
}
