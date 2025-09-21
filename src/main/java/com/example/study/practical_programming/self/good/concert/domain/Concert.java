package com.example.study.practical_programming.self.good.concert.domain;

import java.util.List;

public class Concert {
    private Long concertId;
    private List<Seat> seatList;

    public long calculateRevenue() {
        return this.seatList
                .stream()
                .filter(seat -> seat.getSeatStatus().equals(SeatStatus.PAID))
                .map(Seat::getOriginPrice).reduce(0L, Long::sum);
    }

    public long calculateProfits() {
        return this.seatList
                .stream()
                .filter(seat -> seat.getSeatStatus().equals(SeatStatus.PAID))
                .map(s -> s.getOriginPrice() - s.getTaxPrice()).reduce(0L, Long::sum);
    }
}