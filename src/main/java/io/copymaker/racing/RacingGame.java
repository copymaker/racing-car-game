package io.copymaker.racing;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.number.NumberGenerator;
import io.copymaker.racing.number.RandomNumberGenerator;
import io.copymaker.racing.track.Track;
import io.copymaker.racing.view.View;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacingGame {

    private Track track;
    private NumberGenerator numberGenerator;
    private View view;

    public RacingGame(View view) {
        this.view = view;
    }

    public void start() {
        String[] names = splitWithDelimiter(view.inputCarNames(), ",");
        int laps = view.inputTotalLaps();

        startGame(names, laps);
    }

    // TODO: view 와 track 을 분리할 방법
    private void startGame(String[] names, int laps) {
        readyGame(names, laps);
        track.startRace(numberGenerator);
    }

    private void readyGame(String[] names, int laps) {
        setTrack(new Track(laps));
        setCars(createCars(names));
        setNumberGenerator(new RandomNumberGenerator());
    }

    private void setTrack(Track track) {
        this.track = track;
    }

    private void setCars(List<Car> cars) {
        track.setCars(cars);
    }

    private List<Car> createCars(String[] names) {
        return Stream.of(names)
                    .map(name -> new Car(name))
                    .collect(Collectors.toList());
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    // TODO: util 후보
    private String[] splitWithDelimiter(String str, String delimiter) {
        return str.split(delimiter);
    }
}
