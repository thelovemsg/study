package com.example.study.self.bank.service;

import com.example.study.self.bank.domain.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public interface ExchangeRateService {
    BigDecimal getExchangeRate(Currency currency) throws IOException, InterruptedException;
    Money convert(Money money, Currency currency) throws IOException, InterruptedException;
}
