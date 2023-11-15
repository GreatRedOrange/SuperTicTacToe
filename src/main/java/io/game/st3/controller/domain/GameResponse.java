package io.game.st3.controller.domain;

import io.game.st3.domain.Board;
import io.game.st3.domain.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {

    private Board gameBoard;
    private Coordinate outerBoardCoordinate;

}
