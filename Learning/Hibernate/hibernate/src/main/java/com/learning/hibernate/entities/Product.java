package com.learning.hibernate.entities;

import com.learning.hibernate.values.Money;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "Products")
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
@SequenceGenerator(name = "pk_gen", sequenceName = "product_pk_gen", initialValue = 300, allocationSize = 3)
public class Product extends SequencedExampleEntity {
    
    @NonNull private String name;

    @NonNull private String description;

    @Transient
    private String transientField;

    @NonNull
    @Column(name = "is_stocked")
    private boolean isStocked;

    @NonNull
    @Embedded
    @AttributeOverrides(
    {
        @AttributeOverride(name = "amount", column = @Column(name = "amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;


}
