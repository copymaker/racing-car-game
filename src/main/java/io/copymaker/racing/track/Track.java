package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Track {

    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void forwardCars(Supplier<Integer> supplier) {
        cars.stream().forEach(car -> car.forward(supplier.get()));
    }

    public List<Car> finishLine() {
        List<Car> winningCars = new ArrayList<>();

        cars.sort((a, b) -> Integer.compare(b.getDistance(), a.getDistance()));
        for (Car car : cars) {
            if (car.getDistance() == cars.get(0).getDistance()) {
                winningCars.add(car);
                continue;
            }
            break;
        }

        return winningCars;
    }
}
