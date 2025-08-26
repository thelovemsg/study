package com.example.study.self.bank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Money {

    private BigDecimal amount;

    public static Money createMoney(BigDecimal amount) {
        return new Money(amount);
    }

    public Money subtract(Money subtractingMoney) {
        BigDecimal subtractedAmount = this.amount.subtract(subtractingMoney.getAmount());
        return Money.createMoney(subtractedAmount);
    }

    public Money add(Money addingMoney) {
        BigDecimal addedAmount = this.amount.add(addingMoney.getAmount());
        return Money.createMoney(addedAmount);
    }

}
