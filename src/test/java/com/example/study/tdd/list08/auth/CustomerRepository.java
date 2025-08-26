package com.example.study.tdd.list08.auth;

public interface CustomerRepository {
    Customer findOne(String id);
}