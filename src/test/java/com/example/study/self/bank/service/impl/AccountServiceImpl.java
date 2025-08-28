package com.example.study.self.bank.service.impl;

import com.example.study.self.bank.domain.Account;
import com.example.study.self.bank.service.AccountFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountServiceImpl {

    private final AccountFactory accountFactory;

    public Account makeNewAccount() {
        return accountFactory.createAccount();
    }
}
