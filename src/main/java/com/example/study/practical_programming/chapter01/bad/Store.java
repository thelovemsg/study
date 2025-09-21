package com.example.study.practical_programming.chapter01.bad;

import lombok.Getter;

import java.util.List;

@Getter
public class Store {

    private List<Order> orders;
    private long rentalFee;
}
