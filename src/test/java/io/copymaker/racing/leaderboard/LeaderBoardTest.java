package io.copymaker.racing.leaderboard;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.number.RandomNumberGenerator;
import io.copymaker.racing.track.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LeaderBoardTest {

    private LeaderBoard leaderBoard;

    @BeforeEach
    void setUp() {
        leaderBoard = new LeaderBoard();
    }

    @Test
    void shouldAddedCarAndDistanceWhenCallRecord() {
        Car car1 = new Car("car1");
        AtomicInteger distance = new AtomicInteger(5);

        Track track = new Track(5);
        track.addCar(new Car("car1"));
        track.addCar(new Car("car2"));
        track.addCar(new Car("car3"));
        track.addCar(new Car("car4"));

        track.startRace(new RandomNumberGenerator());
    }

}