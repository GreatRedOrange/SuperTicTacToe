package io.game.superttc.service.validation;

import org.springframework.stereotype.Service;

@Service
public class WinValidator {

    public static boolean checkWin(char[][] board, char player, int row, int col) {
        // Check the small board for a win
        int startRow = row * 3;
        int startCol = col * 3;
        for (int i = 0; i < 3; i++) {
            if (board[startRow + i][startCol] == player && board[startRow + i][startCol + 1] == player
                    && board[startRow + i][startCol + 2] == player) {
                return true; // Row win
            }
            if (board[startRow][startCol + i] == player && board[startRow + 1][startCol + i] == player
                    && board[startRow + 2][startCol + i] == player) {
                return true; // Column win
            }
        }
        if (board[startRow][startCol] == player && board[startRow + 1][startCol + 1] == player
                && board[startRow + 2][startCol + 2] == player) {
            return true; // Diagonal win
        }
        if (board[startRow][startCol + 2] == player && board[startRow + 1][startCol + 1] == player
                && board[startRow + 2][startCol] == player) {
            return true; // Diagonal win
        }
        return false;
    }

}
