package com.example.study.study.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Car {
    public static List<Car> createCars() {
        List<Car> result = new ArrayList<>();
        result.add(new Car("tesla", 50, "123222", Color.RED));
        result.add(new Car("hyundai", 30, "123333", Color.PURPLE));
        result.add(new Car("shaomi", 60, "123444", Color.YELLOW));
        return result;
    }

    public void showCarInfo() {
        System.out.println(this);
    }

    public enum Color {
        RED, YELLOW, PURPLE;
    }

    private String name;
    private int speed;
    private String number;
    private Color color;

    public static int compareBySpeed(Car a, Car b) {
        return a.getSpeed() > a.getSpeed() ? 1 : a.getSpeed() == a.getSpeed() ? 0 : -1;
    }

}
