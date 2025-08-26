package com.example.study.tdd.list08.subs;

public interface SubscriptionDao {
    Subscription selectByUser(String id);

    void insert(Subscription subscription);
}