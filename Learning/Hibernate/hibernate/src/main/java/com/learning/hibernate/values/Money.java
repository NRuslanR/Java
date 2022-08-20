package com.learning.hibernate.values;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Embeddable
public class Money {
    
    private final BigDecimal amount;
    private final String currency;
    
}
