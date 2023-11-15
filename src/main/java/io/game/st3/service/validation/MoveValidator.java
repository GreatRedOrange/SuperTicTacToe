package io.game.st3.service.validation;

import org.springframework.stereotype.Service;

import io.game.st3.domain.Coordinate;

@Service
public class MoveValidator {

    public static final int BOARD_SIZE = 3;
    public static final int ROW_START = 0;

    public static boolean isValidMove(char[][] board, Coordinate coordinate, int[] nextMove) {
        if (nextMove[0] == -1 || nextMove[1] == -1) {
            return board[coordinate.getRow()][coordinate.getCol()] == '\0';
        } else {
            return board[coordinate.getRow()][coordinate.getCol()] ==
                    '\0' && (coordinate.getRow() / BOARD_SIZE == nextMove[ROW_START] && coordinate.getCol() / BOARD_SIZE == nextMove[1]);
        }
    }
}
