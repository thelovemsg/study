package com.example.study.tdd.list08;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayInfo {
    private final String datetime;
    private final String trNum;
    private final int amounts;

}