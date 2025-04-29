package com.example.study.study.lambda;

import java.util.List;

public class RunTest {
    public static void main(String[] args) {
        List<Car> cars = Car.createCars();

        for (Car car : cars) {
            car.showCarInfo();
        }

        // Approach 1: Create Methods that Search for Persons that Match One
        System.out.println("over 50 speed");
        CarTest.printCarFasterThan(cars, 50);
        System.out.println();

        System.out.println("low 40 and high 55 speed");
        CarTest.showCarsWithinSpeed(cars, 40, 55);

        // Approach 3: Specify Search Criteria Code in a Local Class
        System.out.println("show Car with new class!");
        CarTest.showCar(cars, new CheckCarOverThirtySpeedWithColor());

        // Approach 4: Specify Search Criteria Code in an Anonymous Class

        CarTest.showCar(cars, new CarTest.CheckCar() {
            @Override
            public boolean test(Car c) {
                return c.getSpeed() > 100;
            }
        });
        // Approach 5: Specify Search Criteria Code with a Lambda Expression
        CarTest.showCar(cars, (Car c) -> c.getSpeed() > 100);
        CarTest.showCar(cars, c -> c.getSpeed() > 100);

        // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
        System.out.println("approach 6");
        CarTest.showCarWithPredicate(cars, c -> c.getSpeed() > 100);
        System.out.println("");

        // Approach 7: Use Lambda Expressions Throughout Your Application
        System.out.println("approach 7");
        CarTest.processCars(cars, c -> c.getSpeed() > 50, c -> c.showCarInfo());
    }
}
