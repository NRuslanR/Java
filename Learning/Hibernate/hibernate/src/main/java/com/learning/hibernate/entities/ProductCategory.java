package com.learning.hibernate.entities;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "product_categories")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class ProductCategory extends ExampleEntity {

    public ProductCategory(String name)
    {
        setName(name);
    }

    public ProductCategory(String name, Collection<CategorizedProduct> products)
    {
        setName(name);
        setProducts(products);
    }

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @JoinColumn(name = "category_id", nullable = false)
    private Set<CategorizedProduct> products;

    public Collection<CategorizedProduct> getProducts()
    {
        return products;
    }

    public void setProducts(Collection<CategorizedProduct> products)
    {
        this.products = products.stream().collect(Collectors.toSet());
    }
}
