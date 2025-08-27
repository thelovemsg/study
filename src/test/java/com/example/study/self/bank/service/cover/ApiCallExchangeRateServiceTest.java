package com.example.study.self.bank.service.cover;

import com.example.study.self.bank.service.ExchangeRateService;
import com.example.study.self.bank.service.impl.ExchangeRateServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.Locale;

import static org.mockito.Mockito.*;

public class ApiCallExchangeRateServiceTest {

    @Test
    void apiCallTest() throws IOException, InterruptedException {
        HttpClient mockClient = mock(HttpClient.class);
        HttpResponse mockResponse = mock(HttpResponse.class);

        String jsonResponse = """
            {
                "rates": {
                    "KRW": 1300.00,
                    "USD": 1.0
                }
            }
        """;

        when(mockResponse.body()).thenReturn(jsonResponse);
        when(mockClient.send(any(HttpRequest.class), any())).thenReturn(mockResponse);

        ExchangeRateService service = new ExchangeRateServiceImpl(mockClient, new Gson());

        BigDecimal rate = service.getExchangeRate(Currency.getInstance(Locale.KOREA), Currency.getInstance(Locale.US));

        Assertions.assertEquals(new BigDecimal("1"), rate);
    }

}
