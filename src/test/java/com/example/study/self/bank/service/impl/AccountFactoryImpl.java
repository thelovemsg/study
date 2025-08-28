package com.example.study.self.bank.service.impl;

import com.example.study.self.bank.domain.Account;
import com.example.study.self.bank.service.AccountFactory;
import com.example.study.self.bank.service.generator.AccountIdGenerator;
import com.example.study.self.bank.service.generator.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountFactoryImpl implements AccountFactory {

    private final AccountIdGenerator accountIdGenerator;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    public Account createAccount() {
        return Account.builder()
                .accountId(accountIdGenerator.getNewId())
                .accountNumber(accountNumberGenerator.getNewNumber())
                .build();
    }
}
