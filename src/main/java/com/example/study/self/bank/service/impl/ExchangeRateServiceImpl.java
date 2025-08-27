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

    public ExchangeRateServiceImpl(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    @Override
    public BigDecimal getExchangeRate(Currency from, Currency to) throws IOException, InterruptedException {

        String fromCode = from.getCurrencyCode();
        String toCode = to.getCurrencyCode();

        String completedUrl = String.join("/", BASE_URL, fromCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(completedUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        JsonObject rates = json.getAsJsonObject(RATE);

        double rate = rates.get(toCode).getAsDouble();
        return new BigDecimal(rate);

    }

    @Override
    public BigDecimal getExchangeRate(Currency from, Currency to, int precision) throws IOException, InterruptedException {
        BigDecimal exchangeRate = this.getExchangeRate(from, to);
        return exchangeRate.setScale(precision, RoundingMode.HALF_UP);
    }

    @Override
    public Money convert(Money money, Currency from, Currency to) throws IOException, InterruptedException {
        BigDecimal rate = this.getExchangeRate(from, to);
        BigDecimal convertedAmount = money.getAmount().multiply(rate);

        // 대상 통화에 맞는 정밀도 적용
        Currency target = Currency.getInstance(from.getCurrencyCode());
        int scale = target.getDefaultFractionDigits();
        convertedAmount = convertedAmount.setScale(scale, RoundingMode.HALF_UP);

        return Money.of(convertedAmount, to.getCurrencyCode());
    }

}
