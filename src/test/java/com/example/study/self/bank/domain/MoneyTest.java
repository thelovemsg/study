package com.example.study.self.bank.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/***
 *
 * 우선 돈이란... 자고로 더하고 빼고 해야한다.
 * money 객체를 통해서 더하고 빼는 작업이 되는지 확인할 것이다.
 * 0원을 더하는 일은 없으니, Money 객체 생성시에는 액수는 필수이다.
 *
 * 그리고 돈의 경우 method chaining 이 되도록 구현하는게 좋을 것 같다.
 * 아무래도 돈을 더하고 빼고 따로따로 하지말고, 한번에 할 수도 있지 않을까?
 */
class MoneyTest {

    private BigDecimal testAmount;

    @BeforeEach
    void setUp() {
        this.testAmount = new BigDecimal(1_000_000);
    }

    @Test
    @DisplayName("Money 객체 생성 테스트")
    void moneyCreateTest() {
        Money money = Money.createMoney(testAmount);
        Assertions.assertEquals(new BigDecimal(1_000_000), money.getAmount());
    }

    @Test
    @DisplayName("Money 사용 테스트(마이너스)")
    void moneySubtractTest() {
        Money money = Money.createMoney(testAmount);
        Money subtractedMoney = money.subtract(new BigDecimal(1_000_000));
        Assertions.assertEquals(new BigDecimal(0), subtractedMoney.getAmount());
    }

    @Test
    @DisplayName("Money 추가 테스트(플러스)")
    void moneyAddTest() {
        Money money = Money.createMoney(testAmount);
        Money addedMoney = money.add(new BigDecimal(1_000_000));
        Assertions.assertEquals(new BigDecimal(2_000_000), addedMoney.getAmount());
    }

}