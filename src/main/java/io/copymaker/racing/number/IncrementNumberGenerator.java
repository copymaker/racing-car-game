package io.copymaker.racing.number;

public class IncrementNumberGenerator implements NumberGenerator {

    private int i = 0;

    @Override
    public int generate() {
        return i++;
    }
}
