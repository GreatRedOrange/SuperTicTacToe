package io.game.superttc.domain;

import io.game.superttc.domain.enums.CellStatus;
import io.game.superttc.domain.enums.XO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cell {
    private XO xo;
    private CellStatus cellStatus;

    public Cell() {
        this.cellStatus = CellStatus.EMPTY;
    }
}

