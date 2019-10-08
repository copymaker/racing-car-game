package io.copymaker.racing;

import io.copymaker.racing.view.DebugView;
import io.copymaker.racing.view.View;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RacingGameTest {

    @Test
    @DisplayName("게임 실행")
    void gameScenarioTest() {
        // TODO: 입력값 오류시 무한 루프 가능성
        View view = new DebugView("car1,car2,car3,car4,car5", 8);
        RacingGame game = new RacingGame(view);
        game.start();
    }
}