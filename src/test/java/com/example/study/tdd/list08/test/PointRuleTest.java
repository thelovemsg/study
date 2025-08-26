package com.example.study.tdd.list08.test;

import com.example.study.tdd.list08.PointRule;
import com.example.study.tdd.list08.subs.Grade;
import com.example.study.tdd.list08.subs.Product;
import com.example.study.tdd.list08.subs.Subscription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PointRuleTest {

    @Test
    void 만료전_GOLD등급은_130포인트() {
        PointRule rule = new PointRule();
        Subscription s = new Subscription(LocalDate.of(2019, 5, 5), Grade.GOLD);
        Product product = new Product();
        product.setDefaultPoint(20);

        int point = rule.calculate(s, product, LocalDate.of(2019, 5, 5));

        Assertions.assertEquals(130, point);

    }
}
