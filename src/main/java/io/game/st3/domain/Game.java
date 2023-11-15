package io.game.st3.domain;

import java.util.List;
import java.util.UUID;

import io.game.st3.domain.enums.GameStatus;
import io.game.st3.domain.enums.XO;
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
    private XO currentSymbol;
    private List<Coordinate> validPlayBoards;
    private Board gameBoard;
}
