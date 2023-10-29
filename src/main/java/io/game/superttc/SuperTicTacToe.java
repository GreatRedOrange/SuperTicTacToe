package io.game.superttc;

import java.util.Scanner;

public class SuperTicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[9][9];
        int[] nextMove = new int[] { -1, -1 };
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printSuperBoard(board);
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(board, row, col, nextMove)) {
                board[row][col] = currentPlayer;

                int smallRow = row % 3;
                int smallCol = col % 3;

                if (checkWin(board, currentPlayer, smallRow, smallCol)) {
                    printSuperBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull(board, smallRow, smallCol)) {
                    board[row][col] = 'T'; // Mark the small board as a draw
                    if (isBoardFull(board, -1, -1)) {
                        printSuperBoard(board);
                        System.out.println("It's a draw!");
                        gameOver = true;
                    }
                } else {
                    nextMove[0] = smallRow;
                    nextMove[1] = smallCol;
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public static void printSuperBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(char[][] board, int row, int col, int[] nextMove) {
        if (nextMove[0] == -1 || nextMove[1] == -1) {
            return board[row][col] == '\0';
        } else {
            return board[row][col] == '\0' && (row / 3 == nextMove[0] && col / 3 == nextMove[1]);
        }
    }

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
