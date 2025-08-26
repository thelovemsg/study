package com.example.study.tdd.list08.nontestable;

import com.example.study.tdd.list07.domain.User;
import com.example.study.tdd.list08.PointRule;
import com.example.study.tdd.list08.subs.Product;
import com.example.study.tdd.list08.subs.ProductDao;
import com.example.study.tdd.list08.subs.Subscription;
import com.example.study.tdd.list08.subs.SubscriptionDao;
import com.example.study.tdd.list08.test.PointRuleTest;
import lombok.Setter;

import java.time.LocalDate;

public class UserPointCalculator {

    @Setter
    private PointRule pointRule = new PointRule();
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new IllegalArgumentException();
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();

        return PointRule.calculate(s, p, now);
    }
}