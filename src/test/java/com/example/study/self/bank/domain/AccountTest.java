package com.example.study.self.bank.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    @DisplayName("계좌 생성 테스트")
    void createAccount() {
//        Account newAccount = Account.createAccount();
//
//        Assertions.assertNotNull(newAccount.getAccountId());
//        Assertions.assertNotNull(newAccount.getAccountNumber());
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
    @DisplayName("원화와 달러로 금액 보유시 합산 표출 : 달러 + 원화 = 총 금액")
    void wonAndDollarSum() {

    }
}