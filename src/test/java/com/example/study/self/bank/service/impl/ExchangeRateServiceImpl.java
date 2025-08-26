package com.example.study.self.bank.service.impl;

import com.example.study.self.bank.domain.Money;
import com.example.study.self.bank.service.ExchangeRateService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;

public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final HttpClient httpClient;
    private final Gson gson;
    private final String RATE = "rates";
    private final String BASE_URL = "https://api.exchangerate-api.com/v4/latest";

    public ExchangeRateServiceImpl() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public BigDecimal getExchangeRate(Currency currency) throws IOException, InterruptedException {

        String currencyCode = currency.getCurrencyCode();
        String completedUrl = String.join("/", BASE_URL, currencyCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(completedUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        JsonObject rates = json.getAsJsonObject(RATE);

        if (rates.has(currencyCode)) {
            double rate = rates.get(currencyCode).getAsDouble();
            return new BigDecimal(rate);
        }

        throw new IllegalArgumentException("Unknown currency: " + currencyCode);
    }

    @Override
    public Money convert(Money money, Currency currency) throws IOException, InterruptedException {
        BigDecimal rate = this.getExchangeRate(currency);
        BigDecimal convertedAmount = money.getAmount().multiply(rate);

        // 대상 통화에 맞는 정밀도 적용
        Currency target = Currency.getInstance(currency.getCurrencyCode());
        int scale = target.getDefaultFractionDigits();
        convertedAmount = convertedAmount.setScale(scale, RoundingMode.HALF_UP);

        return Money.of(convertedAmount, currency.getCurrencyCode());
    }

}
