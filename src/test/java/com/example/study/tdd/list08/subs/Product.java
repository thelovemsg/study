
package com.example.study.tdd.list08.subs;

public class Product {
    private String id;
    private int defaultPoint;

    public Product(String id) {
        this.id = id;
    }

    public Product() {
    }

    public void setDefaultPoint(int defaultPoint) {
        this.defaultPoint = defaultPoint;
    }

    public String getId() {
        return id;
    }

    public int getDefaultPoint() {
        return defaultPoint;
    }
}