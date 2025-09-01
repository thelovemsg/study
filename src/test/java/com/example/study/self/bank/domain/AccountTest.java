package com.example.study.self.bank.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

class AccountTest {

    private String accountId;
    private String accountNumber;

    @BeforeEach
    void setUp() {
        this.accountId = "test1234";
        this.accountNumber = "1234567890123456";
    }

    @Test
    @DisplayName("계좌 생성 테스트 - id 와 계좌번호 가 올바르게 나오는지 확인")
    void createAccount() {

        Account newAccount = Account.builder().accountId(accountId).accountNumber(accountNumber).build();

        Assertions.assertEquals(newAccount.getAccountId(), accountId);
        Assertions.assertEquals(newAccount.getAccountNumber(), accountNumber);
    }

    @Test
    @DisplayName("account에 저금 : 초기 금액 + 저금 금액 = 총 금액")
    void deposit() {
        Account newAccount = Account.builder()
                                    .accountId(accountId)
                                    .accountNumber(accountNumber)
                                    .balances(Map.of(Currency.getInstance(Locale.KOREA), Money.of(new BigDecimal(5_000))))
                                    .limitPerDay(Money.of(new BigDecimal(5_000)))
                                    .build();

        Money money = Money.of(new BigDecimal(1_000));
        Account depositedAccount = newAccount.deposit(money);

        Money krwMoney = depositedAccount.getBalances().get(Currency.getInstance(Locale.KOREA));
        Assertions.assertEquals(krwMoney, Money.of(new BigDecimal(6_000)));
    }

    @Test
    @DisplayName("account에서 출금 : 초기 금액 - 출금 금액 = 총 금액")
    void withdraw() {
        Account newAccount = Account.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .balances(Map.of(Currency.getInstance(Locale.KOREA), Money.of(new BigDecimal(5_000))))
                .limitPerDay(Money.of(new BigDecimal(5_000)))
                .build();

        Account withdrawnAccountMoney = newAccount.withdraw(Money.of(new BigDecimal(1_000)));
        Assertions.assertEquals(withdrawnAccountMoney.getBalances().get(Currency.getInstance(Locale.KOREA)), Money.of(new BigDecimal(4_000)));
    }

    @Test
    @DisplayName("특정 환율로 합산시 올바른지 : 달러 + 원화 = 총 금액")
    void wonAndDollarSum() {
        Account newAccount = Account.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .balances(Map.of(Currency.getInstance(Locale.KOREA), Money.of(new BigDecimal(5_000))))
                .limitPerDay(Money.of(new BigDecimal(5_000)))
                .build();

        Account deposited = newAccount.deposit(Money.of(new BigDecimal(1_000), Currency.getInstance(Locale.US)));

        Map<Currency, Money> balances = deposited.getBalances();

        Set<Map.Entry<Currency, Money>> entries = balances.entrySet();
        for (Map.Entry<Currency, Money> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("======================");
        }

    }

    @Test
    @DisplayName("달러 계산후 값이 올바른지 확인")
    void onlyDollarSum() {

    }
}