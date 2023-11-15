package io.game.st3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private Coordinate outerPos;
    private Coordinate innerPos;

}
