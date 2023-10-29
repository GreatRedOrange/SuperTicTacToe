package io.game.superttc;

import java.util.Scanner;

import io.game.superttc.domain.Coordinate;

import static io.game.superttc.service.validation.BoardValidator.isBoardFull;
import static io.game.superttc.service.validation.MoveValidator.isValidMove;
import static io.game.superttc.service.validation.WinValidator.checkWin;

public class SuperTicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[9][9];
        int[] nextMove = new int[] { -1, -1 };
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Coordinate coordinate = new Coordinate(row, col);

            if (isValidMove(board, coordinate, nextMove)) {
                board[row][col] = currentPlayer;

                int smallRow = row % 3;
                int smallCol = col % 3;

                if (checkWin(board, currentPlayer, smallRow, smallCol)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull(board, smallRow, smallCol)) {
                    board[row][col] = 'T'; // Mark the small board as a draw
                    if (isBoardFull(board, -1, -1)) {
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
}
