package io.game.superttc.domain;

import io.game.superttc.domain.enums.BoardStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Board {

    private Object[][] boardSpace;
    private BoardStatus boardStatus;

    public Board() {
        boardSpace = new Object[3][3];
        boardStatus = BoardStatus.NEW;
    }
}
