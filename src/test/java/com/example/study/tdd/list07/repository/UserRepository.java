package com.example.study.tdd.list07.repository;

import com.example.study.tdd.list07.domain.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
