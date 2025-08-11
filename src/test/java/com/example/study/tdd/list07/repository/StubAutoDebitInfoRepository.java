package com.example.study.tdd.list07.repository;

import com.example.study.tdd.list07.domain.AutoDebitInfo;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {
    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}