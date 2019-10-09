package io.copymaker.racing.car;

import java.util.Objects;

public class Car implements Comparable<Car> {

    private final int THRESHOLD_FACTOR = 4;

    private String name;

    public Car(String name) {
        if (name == null || name.equals("") || name.length() > 5) {
            throw new IllegalArgumentException("자동차의 이름은 5자 이하의 문자열이어야 합니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int forward(int distance) {
        if (!isValidRange(distance)) {
            return 0;
        }
        return distance >= THRESHOLD_FACTOR ? distance : 0;
    }

    private boolean isValidRange(int distance) {
        return distance >= 0 && distance <= 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Car other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
