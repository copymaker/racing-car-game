package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class TrackTest {

    private final int LAP_COUNT = 5;
    private Track track;
    private Car car1, car2, car3, car4;

    private final Supplier<Integer> incrementSupplier = new Supplier<Integer>() {
        private int i = 1;
        @Override
        public Integer get() {
            return i++;
        }
    };

    private final Supplier<Integer> oddSupplier = new Supplier<Integer>() {
        private int i = 1;
        @Override
        public Integer get() {
            return (i++) % 2;
        }
    };

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
    }

    @Test
    @DisplayName("트랙은 N개의 자동차를 가질 수 있다.")
    void shouldTrackHasCarsWhenAddCarTest() {
        assertThat(track.getCars()).containsExactly(car1, car2, car3, car4);
    }

    @Test
    @DisplayName("트랙은 자동차들을 이동거리만큼 전진 시킬 수 있다.")
    void shouldCarsForwardWhenTrackCallForwardsCarsTest() {
        int distance = 5;
        track.setSupplier(() -> distance);
        track.forwardCars();

        assertThat(track.getCars()).extracting("distance")
                .containsExactly(distance, distance, distance, distance);
    }

    @Test
    @DisplayName("트랙은 각각의 자동차들을 각각의 이동거리만큼 전진 시킬 수 있다.")
    void shouldEachCarsForwardWhenTrackCallForwardsCarsTest() {
        track.setSupplier(incrementSupplier);
        track.forwardCars();

        assertThat(track.getCars()).extracting("distance").containsOnly(1, 2, 3, 4);
    }

    @Test
    @DisplayName("가장 멀리 이동한 하나 이상의 자동차를 반환한다.")
    void shouldReturnMaxDistanceCarWhenCallFinishLineTest() {
        track.setSupplier(oddSupplier);
        track.forwardCars();
        track.forwardCars();
        track.forwardCars();

        assertThat(track.finishLine()).extracting("name", "distance")
                .containsExactly(tuple("car1", 3), tuple("car3", 3));
    }

    @Test
    @DisplayName("랩 수 만큼 자동차를 전진 또는 정지 시킨다.")
    void shouldRepeatForwardCarsUntilTracksLapCount() {
        int distance = 5;
        int finalDistance = distance * LAP_COUNT;
        track.setSupplier(() -> distance);
        track.startRace();

        assertThat(track.getCars()).extracting("distance")
                .containsExactly(finalDistance, finalDistance, finalDistance, finalDistance);
    }

}