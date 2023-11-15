package io.game.st3.service.validation;

import org.springframework.stereotype.Service;

import io.game.st3.domain.Board;
import io.game.st3.domain.enums.XO;

@Service
public class WinValidator {

    public XO getWonXO(Board[][] board) {

        for (int i = 0; i < board.length; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) {
                return board[i][0].getXo();
            }
            if (checkLine(board[0][i], board[1][i], board[2][i])) {
                return board[0][i].getXo();
            }
        }

        if (checkLine(board[0][0], board[1][1], board[2][2])) {
            return board[0][0].getXo();
        }
        if (checkLine(board[0][2], board[1][1], board[2][0])) {
            return board[0][2].getXo();
        }

        return XO.EMPTY;
    }

    private boolean checkLine(Board cell1, Board cell2, Board cell3) {
        return (cell1.getXo() != XO.EMPTY && cell1.getXo() == cell2.getXo() && cell2.getXo() == cell3.getXo());
    }
}
