package com.example.study.practical_programming.self.bad.concert.repository;

import com.example.study.practical_programming.self.bad.concert.domain.Seat;

import java.util.List;

public interface SeatRepository {
    List<Seat> findByConcertId(long concertId);
}
