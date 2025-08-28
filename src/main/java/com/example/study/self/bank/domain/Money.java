package com.example.study.self.bank.domain;

import com.example.study.self.bank.enums.CurrencyCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount) {
        this.amount = amount;
        this.currency = Currency.getInstance(Locale.KOREA);
    }

    public static Money of(BigDecimal amount) {
        if(amount == null) return Money.of(BigDecimal.ZERO);
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must not be negative");

        return new Money(amount);
    }

    public static Money of(BigDecimal amount, Currency currency) {
        if(amount == null) return Money.of(BigDecimal.ZERO);
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must not be negative");

        return new Money(amount, currency);
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public Money subtract(Money subtractingMoney) {
        if(subtractingMoney == null) throw new IllegalArgumentException("subtractingMoney amount is null");
        if(subtractingMoney.getCurrency() != this.getCurrency()) throw new IllegalArgumentException("currency must be same!");
        BigDecimal subtractedAmount = this.amount.subtract(subtractingMoney.getAmount());
        if(subtractedAmount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("subtractingMoney amount is negative");

        return Money.of(subtractedAmount);
    }

    public Money add(Money addingMoney) {
        if(addingMoney == null) throw new IllegalArgumentException("addingMoney amount is null");
        if(addingMoney.getCurrency() != this.getCurrency()) throw new IllegalArgumentException("currency must be same!");
        BigDecimal addedAmount = this.amount.add(addingMoney.getAmount());

        return Money.of(addedAmount);
    }

    public Money multiply(Money multiplyMoney) {
        if(multiplyMoney == null) throw new IllegalArgumentException("multiplyMoney amount is null");
        if(multiplyMoney.getCurrency() != this.getCurrency()) throw new IllegalArgumentException("currency must be same!");
        BigDecimal multipliedAmount = this.amount.multiply(multiplyMoney.getAmount());

        return Money.of(multipliedAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;

        Money money = (Money) o;

        return amount.compareTo(money.amount) == 0 && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }
}
