package com.learning.hibernate.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "person_id")
public class Customer extends Person 
{
    @NonNull
    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Collection<Order> orders;
}
