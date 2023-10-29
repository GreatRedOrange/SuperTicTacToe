package io.game.superttc.domain;

import io.game.superttc.domain.enums.CellStatus;
import io.game.superttc.domain.enums.XO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cell {

    XO xo;
    CellStatus cellStatus;

}

