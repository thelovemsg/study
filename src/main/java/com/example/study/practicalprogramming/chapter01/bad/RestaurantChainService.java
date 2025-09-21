//package com.example.study.practicalprogramming.chapter01.bad;
//
//import lombok.RequiredArgsConstructor;
//
//import java.util.Iterator;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class RestaurantChainService {
//
//    private final StoreRepository storeRepository;
//
//    public long calculateRevenue(long restaurantId) {
//        List<Store> stores = storeRepository.findByRestaurantId(restaurantId);
//        long revenue = 0;
//        for (Store store : stores) {
//            for (Order order : store.getOrders()) {
//                for (Food food : order.getFoods()) {
//                    revenue += food.getPrice();
//                }
//            }
//        }
//
//        return revenue;
//    }
//
//    public long calculateProfit(long restaurantId) {
//        storeRepository.findByRestaurntId(restaurantId);
//    }
//}
