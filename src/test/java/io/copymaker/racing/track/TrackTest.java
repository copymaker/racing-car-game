package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.number.IncrementNumberGenerator;
import io.copymaker.racing.number.NumberGenerator;
import io.copymaker.racing.number.RandomNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class TrackTest {

    private final int LAP_COUNT = 5;
    private Track track;
    private Car car1, car2, car3, car4;
    private NumberGenerator randomGenerator;
    private NumberGenerator incrementGenerator;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1");
        car2 = new Car("car2");
        car3 = new Car("car3");
        car4 = new Car("car4");

        track = new Track(LAP_COUNT);
        track.addCar(car1);
        track.addCar(car2);
        track.addCar(car3);
        track.addCar(car4);

        randomGenerator = new RandomNumberGenerator();
        incrementGenerator = new IncrementNumberGenerator();
    }

    @Test
    @DisplayName("트랙은 N개의 자동차를 가질 수 있다.")
    void shouldTrackHasCarsWhenAddCarTest() {
        assertThat(track.getCars()).containsExactly(car1, car2, car3, car4);
    }

    @Test
    @DisplayName("트랙은 각각의 자동차들을 각각의 이동거리만큼 전진 시킬 수 있다.")
    void shouldEachCarsForwardWhenTrackCallForwardsCarsTest() {
        track.racingCars(incrementGenerator);

        assertThat(track.getCars()).extracting("distance").containsOnly(1, 2, 3, 4);
    }

    @Test
    @DisplayName("랩 수 만큼 자동차를 전진 또는 정지 시킨다.")
    void shouldRepeatForwardCarsUntilTracksLapCount() {
        int distance = 5;
        int finalDistance = distance * LAP_COUNT;
        track.startRace(incrementGenerator);

        assertThat(track.getCars()).extracting("distance")
                .containsExactly(finalDistance, finalDistance, finalDistance, finalDistance);
    }

}