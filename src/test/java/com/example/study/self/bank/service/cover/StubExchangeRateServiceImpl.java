package com.example.study.self.bank.service.cover;

import com.example.study.self.bank.domain.Money;
import com.example.study.self.bank.service.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class StubExchangeRateServiceImpl implements ExchangeRateService {

    private final Map<String, BigDecimal> fixedRates;

    public StubExchangeRateServiceImpl() {
        this.fixedRates = new HashMap<>();
        this.fixedRates.put("USD_KRW", new BigDecimal("1300.00"));
        this.fixedRates.put("EUR_KRW", new BigDecimal("1400.50"));
        this.fixedRates.put("USD_EUR", new BigDecimal("0.85"));
    }

    @Override
    public BigDecimal getExchangeRate(Currency from, Currency to) throws IOException, InterruptedException {
        String key = from.getCurrencyCode() + "_" + to.getCurrencyCode();
        return fixedRates.get(key);
    }

    @Override
    public BigDecimal getExchangeRate(Currency from, Currency to, int precision) throws IOException, InterruptedException {
        String key = from + "_" + to;
        BigDecimal bigDecimal = fixedRates.get(key);
        return bigDecimal.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public Money convert(Money money, Currency from, Currency to) throws IOException, InterruptedException {
        BigDecimal exchangeRate = this.getExchangeRate(from, to);
        BigDecimal convertedAmount = money.getAmount().multiply(exchangeRate);
        return Money.of(convertedAmount);
    }
}
