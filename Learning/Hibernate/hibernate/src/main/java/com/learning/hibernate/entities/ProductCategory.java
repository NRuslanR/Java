package com.learning.hibernate.entities;

import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "product_categories")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class ProductCategory extends ExampleEntity {

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
