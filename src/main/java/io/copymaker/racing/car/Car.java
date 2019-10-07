package io.copymaker.racing.car;

public class Car {

    private String name;
    private int distance;

    public Car(String name) {
        this(name, 0);
    }

    /**
     * TODO: 자동차의 최대 이동거리는 트랙에 의해 결정되는데, 자동차의 생성시점에 distance 의 경계값을 체크할 방법은?
     */
    public Car(String name, int distance) {
        if (name == null || name.equals("") || name.length() > 5) {
            throw new IllegalArgumentException("자동차의 이름은 5자 이내의 문자열이어야 합니다.");
        }
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void forward(int distance) {
        if (distance < 0 || distance > 100) {
            throw new IllegalArgumentException("자동차의 이동거리는 0~100 사이여야 합니다.");
        }
        this.distance += distance;
    }

    public int getDistance() {
        return distance;
    }
}
