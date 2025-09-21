package com.example.study.practicalprogramming.chapter01.bad;

import lombok.Getter;

import java.util.List;

@Getter
public class Store {

    private List<Order> orders;
    private long rentalFee;
}
