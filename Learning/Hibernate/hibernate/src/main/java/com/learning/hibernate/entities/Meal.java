package com.learning.hibernate.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.learning.hibernate.values.Allergens;
import com.learning.hibernate.values.Money;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "meals")
@SecondaryTables({
    @SecondaryTable(
        name = "allergens",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "meal_id")
    ),
    @SecondaryTable(
        name = "moneys",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "meal_id")
    ) 
})
public class Meal extends AbstractExampleEntity<String> {
    
    @Id
    @GeneratedValue(generator = "meal_id_gen")
    @GenericGenerator(
        name = "meal_id_gen",
        parameters = { @Parameter(name = "prefix", value = "HELLO") },
        strategy = "com.learning.hibernate.entities.generators.PrefixedSequencedIdGenerator"
    )
    private String id;

    @NonNull
    private String name;

    @NonNull
    @Embedded
    @AttributeOverrides(
    {
        @AttributeOverride(
            name = "amount", 
            column = 
                @Column(
                    name = "money",
                    table = "moneys"
                )
        ),
        @AttributeOverride(
            name = "currency", 
            column = 
                @Column(
                    name = "curr",
                    table = "moneys"
                )
        )
    }
    )
    private Money price;

    @NonNull
    @Embedded
    private Allergens allergens;

    @Override
    public String getId() {
        
        return id;

    }
}
