package io.copymaker.racing.track;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.util.StrUtil;

import java.util.*;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(map, record.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        map.keySet().stream()
                .sorted()
                .forEach(car -> {
                    int distance = map.get(car);
                    sb.append(StrUtil.repeat("-", distance));
                    sb.append(car.getName());
                    sb.append("("+ distance + ")");
                    sb.append("\n");
                });

        return sb.toString();
    }
}
