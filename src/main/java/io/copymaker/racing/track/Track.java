package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.number.NumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Track {

    private final LeaderBoard leaderBoard = new LeaderBoard();
    private List<Car> cars = new ArrayList<>();
    private NumberGenerator numberGenerator;

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Record racingCars() {
        Map<Car, Integer> map = new HashMap<>();
        for (Car car : cars) {
            map.put(car, car.forward(numberGenerator.generate()));
        }
        leaderBoard.addRecord(map);

        return leaderBoard.getTotalRecord();
    }

    public List<Car> getWinners() {
        return leaderBoard.findWinningCars();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public void checkReady() {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalStateException("자동차는 1대 이상 필요합니다.");
        }
        if (numberGenerator == null) {
            throw new IllegalStateException("숫자 발생기는 반드시 필요합니다.");
        }
    }
}
