package io.game.superttc.controller.domain;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.Coordinate;
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
