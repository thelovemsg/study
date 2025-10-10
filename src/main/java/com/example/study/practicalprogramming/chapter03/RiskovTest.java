package com.example.study.practicalprogramming.chapter03;

public class RiskovTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 5);
        System.out.println("rectangle = " + rectangle.calculateArea());

        Rectangle rectangle2 = new Square(10);
        rectangle2.setHeight(5);

        System.out.println("rectangle2 = " + rectangle2.calculateArea());
    }
}
