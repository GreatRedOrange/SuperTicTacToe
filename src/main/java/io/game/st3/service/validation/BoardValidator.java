package io.game.st3.service.validation;

import org.springframework.stereotype.Service;

@Service
public class BoardValidator {

    public static boolean isBoardFull(char[][] board, int row, int col) {
        if (row == -1 || col == -1) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '\0') {
                        return false;
                    }
                }
            }
            return true;
        } else {
            int startRow = row * 3;
            int startCol = col * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[startRow + i][startCol + j] == '\0') {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
