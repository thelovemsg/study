package com.example.study.practical_programming.self.good.ship;

public class Ship {

    private float speed;

    public void drive() {}
    public void changeDirection(float amount) {}
    public void speedUp(float amount) {
        speed += amount;
        if(speed > 200) speed = 200f;
    }
    public void slowDown(float amount) {
        speed -= amount;
        if(speed < 0) speed = 0f;
    }
}
