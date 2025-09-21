package com.example.study.practical_programming.self.good.concert.domain;

import lombok.Getter;

@Getter
public class Seat {
    private String seatNo;
    private String sectionName;
    private SeatStatus seatStatus;
    private long originPrice;
    private long taxPrice;
}
