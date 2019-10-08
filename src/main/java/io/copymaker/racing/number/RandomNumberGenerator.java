package io.copymaker.racing.number;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random = new Random();
    private final int bound = 9;

    public int generate() {
        return random.nextInt(bound);
    }

}
