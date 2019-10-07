package io.copymaker.racing.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car("TEST");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "AB", "ABC", "ABCD", "ABCDE"})
    @DisplayName("자동차는 이름을 가져야 한다.")
    void shouldCarHasName(String name) {
        car = new Car(name);

        assertThat(car.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "ABCDEFG", "ABCDEFGHIJ"})
    @DisplayName("자동차 이름은 NULL, 공백 제외 5자 이하여야 한다.")
    void shouldThrownExceptionWhenCarNameLengthOver5(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            car = new Car(name);
        });
    }

    @Test
    @DisplayName("자동차의 초기 이동 거리는 0이다.")
    void shouldReturnZeroWhenCreatedCarsDistance() {
        assertThat(car.getDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차는 주어진 숫자만큼 이동해야 한다.")
    void shouldCarMoveWhenForwardWithIntegerValue() {
        car.forward(1);
        assertThat(car.getDistance()).isEqualTo(1);

        car.forward(2);
        assertThat(car.getDistance()).isEqualTo(3);

        car.forward(3);
        assertThat(car.getDistance()).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, Integer.MAX_VALUE})
    @DisplayName("자동차 이동거리는 0~100 사이의 값이어야 한다.")
    void shouldThrownExceptionWhenForwardWithHugeValue(int distance) {
        assertThrows(IllegalArgumentException.class, () -> {
            car.forward(distance);
        });
    }

    @Disabled
    @Test
    @DisplayName("자동차의 최대 이동거리는 트랙의 길이와 같다.")
    void shouldWriteTest() {

    }

}