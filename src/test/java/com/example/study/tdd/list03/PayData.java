package com.example.study.tdd.list03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

}
