package com.example.cleanarchitecture.account.domain;

import java.math.BigInteger;

public class Money {

    public static Money ZERO = Money.of(0L);

    private final BigInteger amount;

    public Money(BigInteger amount) {
        this.amount = amount;
    }

    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public static Money add(Money a, Money b) {
        return new Money(a.amount.add(b.amount));
    }

    public Money negate(){
        return new Money(this.amount.negate());
    }

    public boolean isPositiveOrZero() {
        return this.amount.compareTo(BigInteger.ZERO) >= 0;
    }
}
