package com.learning.hibernate.entities;

import java.util.Collection;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Entity
@Table(name = "shopping_carts")
@ToString(callSuper = true)
@SequenceGenerator(name = "pk_gen", sequenceName = "sc_pk_gen", initialValue = 100)
public class ShoppingCart extends SequencedExampleEntity {
    
    @NonNull
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Collection<ShoppingCartItem> items;

    public ShoppingCart(String name, Collection<ShoppingCartItem> items)
    {
        this.name = name;
        this.items = items;
    }
}
