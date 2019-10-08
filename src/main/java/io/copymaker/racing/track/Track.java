package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Track {

    private final int lapCount;
    private List<Car> cars = new ArrayList<>();
    private Supplier<Integer> supplier;

    public Track(int lapCount) {
        this.lapCount = lapCount;
    }

    public void startRace() {
        for (int i = 0; i < lapCount; i++) {
            forwardCars();
        }
    }

    public void forwardCars() {
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

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setSupplier(Supplier<Integer> supplier) {
        this.supplier = supplier;
    }
}
