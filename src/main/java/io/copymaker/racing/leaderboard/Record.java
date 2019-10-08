package io.copymaker.racing.leaderboard;

import io.copymaker.racing.car.Car;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Record {

    public static final Record EMPTY_RECORD = Record.from(new HashMap<>());

    private final Map<Car, Integer> map;

    static Record from(Map<Car, Integer> map) {
        return new Record(map);
    }

    private Record(Map<Car, Integer> map) {
        this.map = map;
    }

    public List<Car> findLongestDistanceCars() {
        int longestDistance = Collections.max(map.values());

        return map.entrySet().stream()
                .filter(entry -> entry.getValue() == longestDistance)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    public Record merge(Record other) {
        Map<Car, Integer> newMap = Stream.concat(this.map.entrySet().stream(), other.map.entrySet().stream())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), Integer::sum));

        return new Record(newMap);
    }

    @Override
    public String toString() {
        return "Record{" +
                "map=" + map +
                '}';
    }
}
