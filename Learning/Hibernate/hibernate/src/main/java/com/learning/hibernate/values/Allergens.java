package com.learning.hibernate.values;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Embeddable
public class Allergens {
    
    @Column(table = "allergens")
    private final boolean peanuts;
    
    @Column(table = "allergens")
    private final boolean celery;

    @Column(table = "allergens", name = "sesame_seeds")
    private final boolean sesameSeeds;
}
