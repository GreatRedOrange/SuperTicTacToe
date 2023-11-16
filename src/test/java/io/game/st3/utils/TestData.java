package io.game.st3.utils;

import io.game.st3.domain.*;
import io.game.st3.domain.enums.GameStatus;
import io.game.st3.domain.enums.XO;

import static io.game.st3.utils.TestConstants.*;

public class TestData {

    public static Game getNewGame(Board board) {
        return Game.builder()
                .uuid(NEW_GAME_ID)
                .gameBoard(board)
                .player1(getPlayer(FIRST_PLAYER_NAME, XO.X))
                .currentSymbol(XO.X)
                .gameStatus(GameStatus.NEW)
                .build();
    }

    public static Game getFullGame(Board board) {
        return Game.builder()
                .uuid(IN_PROGRESS_GAME_ID)
                .gameBoard(board)
                .player1(getPlayer(FIRST_PLAYER_NAME, XO.X))
                .player2(getPlayer(SECOND_PLAYER_NAME, XO.O))
                .currentSymbol(XO.X)
                .gameStatus(GameStatus.IN_PROGRESS)
                .build();
    }

    public static Player getPlayer(String playerName, XO xo) {
        return Player.builder()
                .name(playerName)
                .xo(xo)
                .build();
    }

    public static Coordinate getCoordinate(int row, int col) {
        return new Coordinate(row, col);
    }

    public static Coordinates getCoordinates(Coordinate outer, Coordinate inner) {
        return new Coordinates(outer, inner);

    }
}
