package com.example.study.tdd.list07.repository;

import com.example.study.tdd.list07.domain.AutoDebitInfo;

public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}