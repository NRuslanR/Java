package com.learning.hibernate.entities;

import com.learning.hibernate.entities.converters.PersonNameAttributeConverter;
import com.learning.hibernate.values.PersonName;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Person extends ExampleEntity {

    public Person(PersonName name, Address address)
    {
        this.name = name;
        this.address = address;
    }

    @NonNull
    @Convert(converter = PersonNameAttributeConverter.class)
    private PersonName name;

    @OneToOne(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
        }, 
        orphanRemoval = true
    )
    @JoinColumn(name = "address_id")
    private Address address;
}
