package io.game.st3.utils;

import io.game.st3.domain.Board;
import io.game.st3.domain.Game;
import io.game.st3.domain.Player;
import io.game.st3.domain.enums.GameStatus;

import static io.game.st3.utils.TestConstants.BOARD_SIZE;
import static io.game.st3.utils.TestConstants.ID;
import static io.game.st3.utils.TestConstants.FIRST_PLAYER_NAME;
import static io.game.st3.utils.TestConstants.SECOND_PLAYER_NAME;

public class TestData {

    public static Board getNullGameBoard() {

        return new Board(new Board[BOARD_SIZE][BOARD_SIZE]);
    }

    public static Game getNewGame(){
        return Game.builder()
                .uuid(ID)
                .gameBoard(getNullGameBoard())
                .player1(getPlayer(FIRST_PLAYER_NAME))
                .gameStatus(GameStatus.NEW)
                .build();
    }

    public static Game getFullGame(){
        return Game.builder()
                .uuid(ID)
                .gameBoard(getNullGameBoard())
                .player1(getPlayer(FIRST_PLAYER_NAME))
                .player2(getPlayer(SECOND_PLAYER_NAME))
                .gameStatus(GameStatus.IN_PROGRESS)
                .build();
    }

    public static Player getPlayer(String playerName) {
        return Player.builder()
                .name(playerName)
                .build();
    }


}
