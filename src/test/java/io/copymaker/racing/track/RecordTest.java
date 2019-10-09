package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RecordTest {

    private Car car1, car2, car3, car4;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1");
        car2 = new Car("car2");
        car3 = new Car("car3");
        car4 = new Car("car4");
    }

    @Test
    @DisplayName("최장 거리를 이동한 자동차는 하나 이상이 될 수 있다.")
    void shouldReturnLongestDistanceCars() {
        Record singleWinnerRecord = Record.from(createMap(1, 2, 3, 4));
        Record multiWinnersRecord = Record.from(createMap(1, 2, 4, 4));

        assertThat(singleWinnerRecord.findLongestDistanceCars()).containsExactly(car4);
        assertThat(multiWinnersRecord.findLongestDistanceCars()).containsExactly(car3, car4);
    }

    @Test
    @DisplayName("Record 는 다른 Record 와 병합 할 수 있다.")
    void shouldReturnNewRecordWhenMerge() {
        Record record1 = Record.from(createMap(1, 2, 3, 4));
        Record record2 = Record.from(createMap(4, 5, null, null));
        Record expectRecord = Record.from(createMap(5, 7, 3, 4));

        assertThat(Record.EMPTY_RECORD.merge(record1)).isEqualTo(record1);
        assertThat(record1.merge(record2)).isEqualTo(expectRecord);
    }

    private Map<Car, Integer> createMap(Integer car1Distance, Integer car2Distance, Integer car3Distance, Integer car4Distance) {
        Map<Car, Integer> map = new HashMap<>();

        if (car1Distance != null) {
            map.put(car1, car1Distance);
        }
        if (car2Distance != null) {
            map.put(car2, car2Distance);
        }
        if (car3Distance != null) {
            map.put(car3, car3Distance);
        }
        if (car4Distance != null) {
            map.put(car4, car4Distance);
        }
        return map;
    }

}