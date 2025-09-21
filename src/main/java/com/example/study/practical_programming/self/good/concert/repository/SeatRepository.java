package com.example.study.practical_programming.self.good.concert.repository;

import com.example.study.practical_programming.self.good.concert.domain.Seat;

import java.util.List;

public interface SeatRepository {
    List<Seat> findByConcertId(long concertId);
}
