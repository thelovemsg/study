package com.example.study.tdd.list07.repository;

import com.example.study.tdd.list07.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
