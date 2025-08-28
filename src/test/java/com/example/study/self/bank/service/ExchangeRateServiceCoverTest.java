package com.example.study.self.bank.service;

import com.example.study.self.bank.domain.Money;
import com.example.study.self.bank.service.cover.StubExchangeRateServiceImpl;
import com.example.study.self.bank.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

class ExchangeRateServiceCoverTest {

    private final ExchangeRateService exchangeRateService = new StubExchangeRateServiceImpl();

    @Test
    @DisplayName("원화 달러 변환시 올바른지")
    void convertWonToDollar() throws IOException, InterruptedException {
        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA));
        Assertions.assertEquals(new BigDecimal("1300.00"),  exchangeRate);
    }

    @Test
    @DisplayName("총 금액이 올바른지")
    void totalAmountTest() throws IOException, InterruptedException {

        BigDecimal targetAmount = new BigDecimal("1300.00");
        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal multiplyAmount = targetAmount.multiply(hundred);

        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA));
        BigDecimal multiplyExchangeAmount = exchangeRate.multiply(hundred);
        Assertions.assertEquals(multiplyAmount,  multiplyExchangeAmount);
    }

    @Test
    @DisplayName("변환 메소드 호출시 결과가 올바른지")
    void convertTest() throws IOException, InterruptedException {
        Money money = Money.of(new BigDecimal("1000.00"));
        Money expectedMoney = money.multiply(Money.of(new BigDecimal("1300.00")));
        Money convertedAmount = exchangeRateService.convert(money, Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA));

        Assertions.assertEquals(expectedMoney, convertedAmount);
    }

    @Test
    @DisplayName("Mockito 로 stubing 구현 - 원화 달러 환율 구하기")
    void getRateByMockito() throws IOException, InterruptedException {

        // Mock 객체 생성
        ExchangeRateService mockService = Mockito.mock(ExchangeRateService.class);

        // Stubbing 설정
        Mockito.when(mockService.getExchangeRate(
                        Currency.getInstance(Locale.US),
                        Currency.getInstance(Locale.KOREA)))
                .thenReturn(new BigDecimal("1300.00"));

        // 테스트 실행
        BigDecimal rate = mockService.getExchangeRate(
                Currency.getInstance(Locale.US),
                Currency.getInstance(Locale.KOREA));

        Assertions.assertEquals(new BigDecimal("1300.00"), rate);
    }

    @Test
    @DisplayName("Mockito를 사용한 mocking - 원화 달러 변환시 올바른지")
    void convertingByMockito() throws IOException, InterruptedException {

        // Mock 객체 생성
        ExchangeRateService mockService = Mockito.spy(new ExchangeRateServiceImpl());

        Mockito.when(mockService.getExchangeRate(Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA))).thenReturn(new BigDecimal("1300.00"));

        Money won = Money.of(new BigDecimal(1000));
        Money convertedDollars = mockService.convert(won, Currency.getInstance(Locale.US), Currency.getInstance(Locale.KOREA));
        Money expectedDollars = Money.of(new BigDecimal("1300000.00"));

        // 테스트 실행

        Assertions.assertEquals(expectedDollars, convertedDollars);
    }

}
