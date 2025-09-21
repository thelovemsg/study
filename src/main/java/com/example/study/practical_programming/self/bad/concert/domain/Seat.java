package com.example.study.practical_programming.self.bad.concert.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Seat {
    private String seatNo;
    private String sectionName;
    private SeatStatus seatStatus;
    private long originPrice;
    private long taxPrice;
}
