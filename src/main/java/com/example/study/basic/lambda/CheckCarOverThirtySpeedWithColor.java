package com.example.study.basic.lambda;

public class CheckCarOverThirtySpeedWithColor implements CarTest.CheckCar {
    @Override
    public boolean test(Car c) {
        return c.getColor() == Car.Color.RED && c.getSpeed() > 30;
    }
}
