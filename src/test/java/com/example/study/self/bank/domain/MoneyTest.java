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
 * => 다시 보니 이미 구현되어 있음!
 *
 * 개선점
 * 1. money는 money끼리 더하는게 올바르다.
 * 타입 안정성 보장 및 도메인 표현력이 상승한다.
 *
 * 생각을 해보면 돈이 돈끼리 더하려고 내가 money객체를 만들엇는데, BigDecimal 로 직접 사용하면 필요없지 않나?
 *
 * 그리고 BigDecimal은 그냥 money용으로 쓰이는것 뿐만 아니라 다양한 곳에서 사용되기 때문에 타입 안정성이 떨어진다.
 *
 * 그러면 내부적으로는 BigDecimal 직접 쓰지 않냐? 그렇다! 그것이 바로 캡슐화의 핵심이다.
 *
 * 우리가 올바른 객체를 매개변수로 넘겨주면 Money가 내부적으로 잘 작동하는 것이다.
 *
 * 이것이 바로 Tell! don't ask 이다. 객체란 새로운 세상을 창조하는 행위이다.
 *
 * 사실 Money가 Money끼리 더하고 빼지는 않는다.
 *
 * 다만 우리가 그렇게 작동하도록 세계를 구현한 것이다.
 *
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
        BigDecimal creatingAmount = new BigDecimal(1_000_000);

        Assertions.assertEquals(creatingAmount, money.getAmount());
    }

    @Test
    @DisplayName("Money 사용 테스트(마이너스)")
    void moneySubtractTest() {
        Money money = Money.createMoney(testAmount);
        Money subtractingMoney = Money.createMoney(new BigDecimal(1_000_000));
        Money subtractedMoney = money.subtract(subtractingMoney);

        Assertions.assertEquals(new BigDecimal(0), subtractedMoney.getAmount());
    }

    @Test
    @DisplayName("Money 추가 테스트(플러스)")
    void moneyAddTest() {
        BigDecimal addingAmount = new BigDecimal(1_000_000);
        Money money = Money.createMoney(testAmount);
        Money addingMoney = Money.createMoney(addingAmount);
        Money addedMoney = money.add(addingMoney);

        Assertions.assertEquals(new BigDecimal(2_000_000), addedMoney.getAmount());
    }

    @Test
    @DisplayName("Money 더하고 빼고 한번에 테스트(플러스 & 마이너스)")
    void moneyAddSubtractTest() {
        BigDecimal addingAmount = new BigDecimal(1_000_000);
        BigDecimal subtractingAmount = new BigDecimal(1_000);

        Money money = Money.createMoney(testAmount);
        Money addingMoney = Money.createMoney(addingAmount);
        Money subtractingMoney = Money.createMoney(subtractingAmount);

        Money addSubtractTest = money.add(addingMoney).subtract(subtractingMoney);

        Assertions.assertEquals(new BigDecimal(1_999_000), addSubtractTest.getAmount());
    }

}