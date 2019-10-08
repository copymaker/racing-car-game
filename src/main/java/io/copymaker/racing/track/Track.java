package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.leaderboard.LeaderBoard;
import io.copymaker.racing.leaderboard.Record;
import io.copymaker.racing.number.NumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Track {

    private final int laps;
    private final LeaderBoard leaderBoard = new LeaderBoard();

    private List<Car> cars = new ArrayList<>();

    public Track(int laps) {
        this.laps = laps;
    }

    public void startRace(NumberGenerator numberGenerator) {
        for (int i = 0; i < laps; i++) {
            Record totalRecord = racingCars(numberGenerator);

            // TODO: console 출력
            System.out.println("totalRecord: " + totalRecord);
            System.out.println("----------");
        }

        List<Car> winningCars = leaderBoard.findWinningCars();

        // TODO: console 출력
        System.out.println("우승 자동차: " + winningCars);
    }

    public Record racingCars(NumberGenerator numberGenerator) {
        Map<Car, Integer> map = new HashMap<>();
        for (Car car : cars) {
            map.put(car, car.forward(numberGenerator.generate()));
        }
        leaderBoard.addRecord(map);

        return leaderBoard.getTotalRecord();
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

}
