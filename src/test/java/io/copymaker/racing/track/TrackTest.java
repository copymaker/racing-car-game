package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.number.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class TrackTest {

    private Track track;
    private Car car1, car2, car3, car4;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1");
        car2 = new Car("car2");
        car3 = new Car("car3");
        car4 = new Car("car4");

        track = new Track();
        track.setCars(Arrays.asList(car1, car2, car3, car4));
        track.setNumberGenerator(getConstantNumberGenerator());
    }

    @Test
    @DisplayName("트랙은 N개의 자동차를 가질 수 있다.")
    void shouldTrackHasCarsWhenAddCarTest() {
        assertThat(track.getCars()).containsExactly(car1, car2, car3, car4);
    }

    @Test
    @DisplayName("트랙은 각각의 자동차들을 각각의 이동거리만큼 전진 시킬 수 있다.")
    void shouldEachCarsForwardWhenTrackCallForwardsCarsTest() {
        Map<Car, Integer> map = new HashMap<>();
        map.put(car1, 4);
        map.put(car2, 5);
        map.put(car3, 6);
        map.put(car4, 7);
        Record expectRecord = Record.from(map);

        assertThat(track.racingCars()).isEqualTo(expectRecord);
    }

    @Test
    @DisplayName("트랙에 자동차가 없거나, 숫자 발생기가 없으면 예외를 던진다.")
    void shouldThrownExceptionWhenCheckReadyIsFailTest() {
        assertThrows(IllegalStateException.class, () -> {
            track.setCars(null);
            track.checkReady();
        });

        assertThrows(IllegalStateException.class, () -> {
            track.setCars(new ArrayList<>());
            track.checkReady();
        });

        assertThrows(IllegalStateException.class, () -> {
            track.setNumberGenerator(null);
            track.checkReady();
        });
    }

    private NumberGenerator getConstantNumberGenerator() {
        return new NumberGenerator() {

            private int[] numbers = {4, 5, 6, 7};
            private int i = 0;

            @Override
            public int generate() {
                return numbers[i++ % 4];
            }
        };
    }

}