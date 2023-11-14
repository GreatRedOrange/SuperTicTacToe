package io.game.superttc.domain;

import io.game.superttc.domain.enums.XO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Board[][] boardSpace;
    private XO xo;

    public Board(Board[][] board) {
        boardSpace = board;
        xo = XO.EMPTY;
    }
}
