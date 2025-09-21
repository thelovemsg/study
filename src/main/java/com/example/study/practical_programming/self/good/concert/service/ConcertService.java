package com.example.study.practical_programming.self.good.concert.service;

import com.example.study.practical_programming.self.good.concert.domain.Concert;
import com.example.study.practical_programming.self.good.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class ConcertService {

//    private final SeatRepository seatRepository;

    private final ConcertRepository concertRepository;

    public long calculateRevenue(long concertId) {
        Concert concert = concertRepository.findByConcertId(concertId);
        return concert.calculateRevenue();
    }

    public long calculateProfits(long concertId) {
        Concert concert = concertRepository.findByConcertId(concertId);
        return concert.calculateProfits();
    }

}
