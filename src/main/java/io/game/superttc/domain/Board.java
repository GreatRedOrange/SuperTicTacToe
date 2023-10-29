package io.game.superttc.domain;

import io.game.superttc.domain.enums.BoardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Board {
    Object[][] boardSpace;
    BoardStatus boardStatus;

}
