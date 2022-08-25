package com.learning.hibernate.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "order_items")
@AttributeOverride(name = "id", column = @Column(name = "order_item_id"))
public class OrderItem extends ExampleEntity {

    @NonNull
    @OneToOne(cascade = {}) @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @NonNull
    private int quantity;
}
