package com.example.study.practicalprogramming.chapter03;

public class Square extends Rectangle {

    public Square(long length) {
        super(length, length);
    }

    @Override
    public void setWidth(long width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(long height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
