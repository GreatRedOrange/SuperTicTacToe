package io.game.superttc.utils;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.Game;
import io.game.superttc.domain.Player;
import io.game.superttc.domain.enums.BoardStatus;
import io.game.superttc.domain.enums.GameStatus;

import static io.game.superttc.utils.TestConstants.ID;
import static io.game.superttc.utils.TestConstants.FIRST_PLAYER_NAME;
import static io.game.superttc.utils.TestConstants.SECOND_PLAYER_NAME;

public class TestData {

    public static Board getGameBoard() {
        Board board = new Board();
        board.setBoardStatus(BoardStatus.NEW);

        return board;
    }

    public static Game getNewGame(){
        return Game.builder()
                .uuid(ID)
                .gameBoard(getGameBoard())
                .player1(getPlayer(FIRST_PLAYER_NAME))
                .gameStatus(GameStatus.NEW)
                .build();
    }

    public static Game getFullGame(){
        return Game.builder()
                .uuid(ID)
                .gameBoard(getGameBoard())
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
