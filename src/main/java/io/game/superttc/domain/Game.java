package io.game.superttc.domain;

import java.util.UUID;

import io.game.superttc.domain.enums.GameStatus;
import lombok.Data;

@Data
public class Game {
    private UUID uuid;
    private Board gameBoard;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;

}