package io.copymaker.racing;

import io.copymaker.racing.car.Car;
import io.copymaker.racing.leaderboard.LeaderBoard;
import io.copymaker.racing.track.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class RacingGameTest {

    @Test
    @DisplayName("게임 실행 시나리오")
    void gameScenarioTest() {
        RacingGame game = new RacingGame();
        Track track = new Track(5);
        track.addCar(new Car("car1"));
        track.addCar(new Car("car2"));
        track.addCar(new Car("car3"));
        track.addCar(new Car("car4"));

        game.start();

        LeaderBoard board = track.getLeaderBoard();
        List<Car> winningCars = board.findWinningCars();
    }
}