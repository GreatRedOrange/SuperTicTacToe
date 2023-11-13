package io.game.superttc.domain;

import java.util.UUID;

import io.game.superttc.domain.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private UUID uuid;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private Board gameBoard;
}
