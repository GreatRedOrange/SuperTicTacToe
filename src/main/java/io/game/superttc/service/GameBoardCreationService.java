package io.game.superttc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.Cell;
import io.game.superttc.domain.enums.BoardStatus;

@Service
public class GameBoardCreationService {

    public static final int BOARD_ELEMENT_COUNT = 9;

    public Board createBoard(){
        return fillBoardWithElements(new Board(), fillInnerBoardsWithCells());
    }

    private List<Board> fillInnerBoardsWithCells() {
        List<Board> boards = getInnerBoardList();

        for (Board innerBoard : boards) {
            fillBoardWithElements(innerBoard, getCells());
        }

        return boards;
    }

    private List<Cell> getCells() {
        List<Cell> cellList = new ArrayList<>();

        for (int i=0; i< BOARD_ELEMENT_COUNT; i++ ) {
            Cell cell = new Cell();

            cellList.add(cell);
        }

        return cellList;
    }

    private static List<Board> getInnerBoardList() {
        List<Board> innerBoardList = new ArrayList<>();

        for (int i=0; i< BOARD_ELEMENT_COUNT; i++ ) {
            Board innerBoard = new Board();
            innerBoard.setBoardStatus(BoardStatus.NEW);
            innerBoardList.add(innerBoard);
        }

        return innerBoardList;
    }

    private <E> Board fillBoardWithElements(Board board, List<E> elements) {
        int counter = 0;

        for(int i = 0; i <  board.getBoardSpace().length; i++) {
            for (int j = 0; j <  board.getBoardSpace()[i].length; j++) {
                board.getBoardSpace()[i][j] = elements.get(counter);
                counter++;
            }
        }
        return board;
    }
}
