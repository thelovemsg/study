package com.example.study.self.bank.service;

import com.example.study.self.bank.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp() {
        this.exchangeRateService = new ExchangeRateServiceImpl();
    }

    @Test
    @DisplayName("환율 호출 테스트 - KRW -> US")
    void callExchangeRateApiForKRW() throws IOException, InterruptedException {
        BigDecimal krwExchangeRate = this.exchangeRateService.getExchangeRate(Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA));

        Assertions.assertNotNull(krwExchangeRate);
        System.out.println("krwExchangeRate = " + krwExchangeRate);

    }

    /**
     * 내가 직접 검증 로직을 작성해야 하나 했는데,
     * Currency 자체적으로 올바르지 않은 국가 코드를 넘기면 IllegalArgumentException이 발생함.
     */
    @Test
    @DisplayName("환율 호출시 미존재하는 국가 환율 에러")
    void callExchangeRatePrecision() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Currency.getInstance("test"));
    }

}