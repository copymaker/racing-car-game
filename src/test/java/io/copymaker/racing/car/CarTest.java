package io.copymaker.racing.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("자동차의 이동 요청 거리가 임계값 미만일 경우 0을 반환한다.")
    void shouldReturnZeroWhenForwardWithLessThanThresholdFactorTest(int distance) {
        assertThat(car.forward(distance)).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 10, 50, 100})
    @DisplayName("자동차의 이동 요청 거리가 임계값 이상일 경우 요청 거리를 반환한다.")
    void shouldReturnDistanceWhenForwardWithGreaterThanOrEqualThresholdFactorTest(int distance) {
        assertThat(car.forward(distance)).isEqualTo(distance);
    }

    @ParameterizedTest
    @ValueSource(ints = {200, Integer.MAX_VALUE})
    @DisplayName("자동차의 이동 요청 거리가 0~100 이외의 값이면 0 을 반환한다.")
    void shouldThrownExceptionWhenForwardWithHugeValue(int distance) {
        assertThat(car.forward(distance)).isEqualTo(0);
    }

}