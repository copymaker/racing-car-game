package io.copymaker.racing.view;

import java.util.Scanner;

public class ConsoleView implements View {

    public String inputCarNames() {
        System.out.print("자동차 이름을 입력해주세요: ");
        return new Scanner(System.in).nextLine();
    }

    public int inputTotalLaps() {
        System.out.print("이동할 횟수를 입력해주세요: ");
        return new Scanner(System.in).nextInt();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.err.println(message);
    }

}
