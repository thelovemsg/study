package com.example.study.basic.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CarTest {

    interface CheckCar{
        boolean test(Car c);
    }

    // Approach 1: Create Methods that Search for Persons that Match One
    public static void printCarFasterThan(List<Car> cars, int speed) {
        for (Car car : cars) {
            if(car.getSpeed() > speed) {
                car.showCarInfo();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods
    public static void showCarsWithinSpeed(List<Car> cars, int low, int high) {
        for (Car car : cars) {
            if(car.getSpeed() > low && car.getSpeed() < high) {
                car.showCarInfo();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    public static void showCar(List<Car> cars, CheckCar tester) {
        for (Car c : cars) {
            if (tester.test(c)) {
                c.showCarInfo();
            }
        }
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    public static void showCarWithPredicate(List<Car> cars, Predicate<Car> tester) {
        for (Car c : cars) {
            if (tester.test(c)) {
                c.showCarInfo();
            }
        }
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application
    public static void processCars(
            List<Car> cars,
            Predicate<Car> tester,
            Consumer<Car> consumer) {
        for (Car c : cars) {
            if (tester.test(c)) {
                consumer.accept(c);
            }
        }
    }

    // Approach 7, second example
    public static void processCarsWithFunction(
            List<Car> cars,
            Predicate<Car> tester,
            Function<Car, String> mapper,
            Consumer<String> block) {
        for (Car c : cars) {
            if (tester.test(c)) {
                String data = mapper.apply(c);
                block.accept(data);
            }
        }
    }

    // Approach 8: Use Generics More Extensively
    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

}
