package com.example.study.practical_programming.self.bad.concert.service;

import com.example.study.practical_programming.self.bad.concert.domain.Seat;
import com.example.study.practical_programming.self.bad.concert.domain.SeatStatus;
import com.example.study.practical_programming.self.bad.concert.repository.SeatRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

//@Service
@RequiredArgsConstructor
public class ConcertService {

    private final SeatRepository seatRepository;

    public long calculateRevenue(long concertId) {
        List<Seat> seatList = seatRepository.findByConcertId(concertId);

        return seatList
                .stream()
                .filter(seat -> seat.getSeatStatus().equals(SeatStatus.PAID))
                .map(Seat::getOriginPrice).reduce(0L, Long::sum);
    }

    public long calculateProfits(long concertId) {
        List<Seat> seatList = seatRepository.findByConcertId(concertId);

        return seatList
                .stream()
                .filter(seat -> seat.getSeatStatus().equals(SeatStatus.PAID))
                .map(s -> s.getOriginPrice() + s.getTaxPrice()).reduce(0L, Long::sum);
    }

}
