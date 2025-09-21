package com.example.study.practical_programming.self.good.concert.repository;

import com.example.study.practical_programming.self.good.concert.domain.Concert;

public interface ConcertRepository {
    Concert findByConcertId(long concertId);
}
