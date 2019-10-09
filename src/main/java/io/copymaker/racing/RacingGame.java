package io.copymaker.racing;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.track.Record;
import io.copymaker.racing.number.NumberGenerator;
import io.copymaker.racing.number.RandomNumberGenerator;
import io.copymaker.racing.track.Track;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacingGame {

    private Track track;
    private int laps;

    private ConsoleView view = new ConsoleView();

    public void start() {
        readyGame();
        startGame();
    }

    private void readyGame() {
        boolean isReadyTrack = false;
        boolean isReadyLaps = false;

        do {
            isReadyTrack = readyTrack();
        } while (!isReadyTrack);

        do {
            isReadyLaps = readyLaps();
        } while (!isReadyLaps);
    }

    private boolean readyTrack() {
        try {
            setTrack(createNewTrack(view.inputCarNames(), new RandomNumberGenerator()));
            return true;
        } catch (Exception e) {
            view.printErrorMessage(e.getMessage());
            return false;
        }
    }

    private boolean readyLaps() {
        try {
            setLaps(view.inputTotalLaps());
            return true;
        } catch (Exception e) {
            view.printErrorMessage(e.getMessage());
            return false;
        }
    }

    private void startGame() {
        for (int i = 0; i < laps; i++) {
            Record record = track.racingCars();
            view.printBreakLine();
            view.printMessage(record.toString());
        }
        view.printMessage("우승 자동차는 " + track.getWinners() + " 입니다.");
    }

    private Track createNewTrack(String stringNames, NumberGenerator numberGenerator) {
        Track track = new Track();
        track.setCars(createCars(stringNames.split(",")));
        track.setNumberGenerator(numberGenerator);
        return track;
    }

    private List<Car> createCars(String[] names) {
        return Stream.of(names)
                    .map(name -> new Car(name))
                    .collect(Collectors.toList());
    }

    private void setTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track 은 반드시 필요합니다.");
        }
        track.checkReady();
        this.track = track;
    }

    private void setLaps(int laps) {
        if (laps < 1 || laps > 10) {
            throw new IllegalArgumentException("반복 횟수는 1 ~ 10 사이여야 합니다.");
        }
        this.laps = laps;
    }

}
