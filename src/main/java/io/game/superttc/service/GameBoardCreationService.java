package io.game.superttc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.enums.XO;

@Service
public class GameBoardCreationService {

    public static final int BOARD_ELEMENT_COUNT = 9;
    public static final int BOARD_SIZE = 3;

    private final Supplier<Board> boardSupplier = () -> new Board(new Board[BOARD_SIZE][BOARD_SIZE]);
    private final Supplier<Board> cellSupplier = () -> new Board(null);

    public Board createBoard() {
        return fillBoardWithElements(boardSupplier.get(), fillBoardsWithEmptyCells());
    }

    private List<Board> fillBoardsWithEmptyCells() {
        List<Board> innerBoards = getElementList(boardSupplier);

        for (Board innerBoard : innerBoards) {
            fillBoardWithElements(innerBoard, getElementList(cellSupplier));
        }

        return innerBoards;
    }

    private static List<Board> getElementList(Supplier<Board> boardSupplier) {
        List<Board> elementList = new ArrayList<>();

        for (int i = 0; i < BOARD_ELEMENT_COUNT; i++) {
            Board element = boardSupplier.get();
            element.setXo(XO.EMPTY);
            elementList.add(element);
        }

        return elementList;
    }

    private Board fillBoardWithElements(Board board, List<Board> elements) {
        int counter = 0;

        for (int i = 0; i < board.getBoardSpace().length; i++) {
            for (int j = 0; j < board.getBoardSpace()[i].length; j++) {
                board.getBoardSpace()[i][j] = elements.get(counter);
                counter++;
            }
        }
        return board;
    }
}
