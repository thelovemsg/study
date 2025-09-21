package com.example.study.practical_programming.self.good.concert.repository.impl;

import com.example.study.practical_programming.self.good.concert.domain.Seat;
import com.example.study.practical_programming.self.good.concert.repository.SeatRepository;

import java.util.List;

//@Repository
public class SeatRepositoryImpl implements SeatRepository {

    @Override
    public List<Seat> findByConcertId(long concertId) {
        // 원래는 DB와의 연동을 통해 조회해야함. 빈 list 반환으로 수정
        return List.of();
    }

}
