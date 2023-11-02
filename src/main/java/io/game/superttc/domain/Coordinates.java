package io.game.superttc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private Coordinate outerBoardCoordinate;
    private Coordinate innerBoardCoordinate;

}
