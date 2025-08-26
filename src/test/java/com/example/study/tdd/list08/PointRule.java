package com.example.study.tdd.list08;

import com.example.study.tdd.list08.subs.Grade;
import com.example.study.tdd.list08.subs.Product;
import com.example.study.tdd.list08.subs.Subscription;

import java.time.LocalDate;

public class PointRule {

    public static int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if(s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }

        if(s.getGrade() == Grade.GOLD) {
            point += 100;
        }

        return point;
    }
}
