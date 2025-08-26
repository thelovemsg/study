package com.example.study.self.bank.domain;

import com.example.study.self.bank.enums.CurrencyCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

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

    //0원도 돈이라고 생각함. 객체를 생성하고 이상하지 않는 선에서 덧셈과 나눗셈이 되도록 하는게 낫다고 판단.
    public static Money createMoney(BigDecimal amount) {
        if(amount == null) return Money.createMoney(BigDecimal.ZERO);
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must not be negative");

        return new Money(amount);
    }

    public static Money createMoney(BigDecimal amount, Currency currency) {
        if(amount == null) return Money.createMoney(BigDecimal.ZERO);
        if(amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must not be negative");
        return new Money(amount, currency);
    }

    public static Money of(BigDecimal convertedAmount, String currency) {
        return Money.createMoney(convertedAmount, Currency.getInstance(currency));
    }

    public Money subtract(Money subtractingMoney) {
        if(subtractingMoney == null) throw new IllegalArgumentException("subtractingMoney amount is null");

        BigDecimal subtractedAmount = this.amount.subtract(subtractingMoney.getAmount());

        if(subtractedAmount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("subtractingMoney amount is negative");

        return Money.createMoney(subtractedAmount);
    }

    public Money add(Money addingMoney) {
        if(addingMoney == null) throw new IllegalArgumentException("addingMoney amount is null");

        BigDecimal addedAmount = this.amount.add(addingMoney.getAmount());

        return Money.createMoney(addedAmount);
    }

    public Money multiply(BigDecimal multiplyMoney) {
        if(multiplyMoney == null) throw new IllegalArgumentException("multiplyMoney amount is null");
        BigDecimal multipliedAmount = this.amount.multiply(multiplyMoney);
        return Money.createMoney(multipliedAmount);
    }

}
