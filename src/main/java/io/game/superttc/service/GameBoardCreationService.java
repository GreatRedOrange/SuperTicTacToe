package io.game.superttc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.game.superttc.domain.Board;

@Service
public class GameBoardCreationService {

    public static final int BOARD_START = 0;
    public static final int BOARD_FINISH = 2;

    public static final int INNER_BOARD_COUNT = 9;

    public static Board createBoard(){

        Board outerBoard = new Board();
        List<Board> innerBoardList = getInnerBoardList();
        outerBoard = getFilledBoard(outerBoard, innerBoardList);

        return outerBoard;
    }

    private static List<Board> getInnerBoardList() {
        List<Board> innerBoardList = new ArrayList<>();

        for (int i=0; i< INNER_BOARD_COUNT; i++ ) {
            Board innerBoard = new Board();
            innerBoardList.add(innerBoard);
        }

        return innerBoardList;
    }

    private static Board getFilledBoard(Board board, List<Board> innerBoardList) {
        int counter = 0;

        for(int i = 0; i <  board.getBoardSpace().length; i++) {
            for (int j = 0; j <  board.getBoardSpace()[i].length  ; i++) {
                board.getBoardSpace()[i][j] = innerBoardList.get(counter);
                counter++;
            }
        }
        return board;
    }
}
