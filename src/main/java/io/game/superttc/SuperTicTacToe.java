package io.game.superttc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.Cell;
import io.game.superttc.domain.enums.CellStatus;
import io.game.superttc.domain.enums.XO;
import io.game.superttc.service.GameBoardCreationService;

public class SuperTicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[9][9];
        int[] nextMove = new int[] {-1, -1};
        char currentPlayer = 'X';
        boolean gameOver = false;

        Board outerBoard = new Board();
        List<Board> innerBoards = new ArrayList<>();
        List<List<Cell>> cells = new ArrayList<>();

        Cell cell = new Cell();
        Cell cell1 = new Cell();

        cell.setCellStatus(CellStatus.OCCUPIED);
        cell.setXo(XO.X);

        cell.setCellStatus(CellStatus.OCCUPIED);
        cell.setXo(XO.O);

        GameBoardCreationService.createBoard();

        var x = GameBoardCreationService.createBoard();

        System.out.println(x);

    }
}
