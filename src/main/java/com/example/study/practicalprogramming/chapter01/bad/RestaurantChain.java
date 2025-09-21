package com.example.study.practicalprogramming.chapter01.bad;

import java.util.List;

public class RestaurantChain {

    private List<Store> stores;

    public long calculateRevenue() {
        long revenue = 0;
        for (Store store : stores) {
            for (Order order : store.getOrders()) {
                for (Food food : order.getFoods()) {
                    revenue += food.getPrice();
                }
            }
        }

        return revenue;
    }

    public long calculateProfit() {
        long cost = 0;

        for (Store store : stores) {
            for (Order order : store.getOrders()) {
                long orderPrice = 0;
                for (Food food : order.getFoods()) {
                    orderPrice += food.getPrice();
                    cost += food.getOriginCost();
                }

                cost += orderPrice * order.getTransactionFeePercent();
            }
            cost += store.getRentalFee();
        }

        return calculateRevenue() - cost;
    }
}
