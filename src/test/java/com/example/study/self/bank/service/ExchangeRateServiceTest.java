package com.example.study.self.bank.service;

import com.example.study.self.bank.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.Currency;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp() {
        this.exchangeRateService = new ExchangeRateServiceImpl();
    }

    @Test
    @DisplayName("환율 호출 테스트")
    void callExchangeRateApi() throws IOException, InterruptedException {
        BigDecimal exchangeRate1 = exchangeRateService.getExchangeRate(Currency.getInstance(Locale.KOREA));
    }

}