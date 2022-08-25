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
    
    public static Money ofUSD(long amount)
    {
        return of(amount, "USD");
    }

    public static Money ofEUR(long amount)
    {
        return of(amount, "EUR");
    }

    public static Money ofUSD(double amount)
    {
        return of(amount, "USD");
    }

    public static Money ofEUR(double amount)
    {
        return of(amount, "EUR");
    }

    public static Money of(long amount, String currency)
    {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    public static Money of(double amount, String currency)
    {
        return new Money(BigDecimal.valueOf(amount), currency);
    }


}
