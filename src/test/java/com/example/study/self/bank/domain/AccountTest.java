package com.example.study.self.bank.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    @DisplayName("계좌 생성 테스트 - id 와 계좌번호 가 올바르게 나오는지 확인")
    void createAccount() {
        String accountId = "test1234";
        String accountNumber = "1234567890123456";

        Account newAccount = Account.builder().accountId(accountId).accountNumber(accountNumber).build();

        Assertions.assertEquals(newAccount.getAccountId(), accountId);
        Assertions.assertEquals(newAccount.getAccountNumber(), accountNumber);
    }

    @Test
    @DisplayName("계좌 생성 테스트 - 중복된 계좌번호면 새롭게 번호를 다시 채번")
    void createDuplicateNumberAccount() {
//        Account newAccount = Account.createAccount();
//
//        Assertions.assertNotNull(newAccount.getAccountId());
//        Assertions.assertNotNull(newAccount.getAccountNumber());
    }

    @Test
    @DisplayName("account에 저금 : 초기 금액 + 저금 금액 = 총 금액")
    void deposit() {

    }

    @Test
    @DisplayName("account에서 출금 : 초기 금액 - 출금 금액 = 총 금액")
    void withdraw() {

    }

    @Test
    @DisplayName("특정 환율로 합산시 올바른지 : 달러 + 원화 = 총 금액")
    void wonAndDollarSum() {

    }

    @Test
    @DisplayName("달러 계산후 값이 올바른지 확인")
    void onlyDollarSum() {

    }
}