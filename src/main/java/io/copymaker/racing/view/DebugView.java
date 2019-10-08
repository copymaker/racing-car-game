package io.copymaker.racing.view;

public class DebugView implements View {

    private final String names;
    private final int laps;

    public DebugView(String names, int laps) {
        this.names = names;
        this.laps = laps;
    }

    public String inputCarNames() {
        System.out.print("자동차 이름을 입력해주세요: ");
        System.out.println(names);
        return names;
    }

    public int inputTotalLaps() {
        System.out.print("이동할 횟수를 입력해주세요: ");
        System.out.println(laps);
        return laps;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.err.println(message);
    }
}
