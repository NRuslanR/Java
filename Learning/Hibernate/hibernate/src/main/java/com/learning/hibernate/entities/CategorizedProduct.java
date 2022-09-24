package com.learning.hibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "categorized_products")
public class CategorizedProduct extends ExampleEntity {
    
    @NonNull
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private ProductCategory category;
}
