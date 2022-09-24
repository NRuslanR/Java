package com.learning.hibernate.entities;

import com.learning.hibernate.values.Money;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString(callSuper = true)
@Entity
@Table(name = "shopping_cart_items")
@SequenceGenerator(name = "pk_gen", sequenceName = "sc_item_pk_gen", initialValue = 1000)
public class ShoppingCartItem extends SequencedExampleEntity {
    
    @NonNull
    private int quantity;

    @NonNull
    @Column(name = "product_name")
    private String productName;

    @NonNull
    @Column(name = "product_price")
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "product_price_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "product_price_currency"))
    })
    private Money productPrice;

    public static ShoppingCartItem of(int quantity, String productName, Money productPrice)
    {
        return new ShoppingCartItem(quantity, productName, productPrice);
    }

}
