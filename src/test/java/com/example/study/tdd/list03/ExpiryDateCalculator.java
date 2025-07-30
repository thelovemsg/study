package com.example.study.tdd.list03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10000;
        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBilling(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private static LocalDate expiryDateUsingFirstBilling(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            if(dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }

            return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
        } else {
            return candidateExp;
        }
    }
}
