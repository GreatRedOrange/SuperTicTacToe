package io.game.st3.service;

import io.game.st3.domain.Board;
import io.game.st3.domain.enums.XO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WinServiceTest {

    private Board board;
    @Autowired
    private WinService winService;
    @Autowired
    private GameBoardCreationService gameBoardCreationService;

    @BeforeEach
    void setup() {
        board = gameBoardCreationService.createBoard();

    }

    @Test
    void testWinServiceSetsXOCorrectlyForOuterBoard() {
        mutateBoardForGameWin(board);

        var actualResult = winService.checkGameWin(board);

        assertEquals(XO.X, board.getXo());
        assertTrue(actualResult);
    }

    @Test
    void testWinServiceSetsXOCorrectlyForInnerBoard() {
        mutateBoardForWinInFirstInnerBoard(board);

        winService.checkGameWin(board);

        assertEquals(XO.X, board.getBoardSpace()[0][0].getXo());
    }

    private Board mutateBoardForGameWin(Board board) {
        board.getBoardSpace()[0][0].setXo(XO.X);
        board.getBoardSpace()[0][1].setXo(XO.X);
        board.getBoardSpace()[0][2].setXo(XO.X);

        return board;
    }

    private Board mutateBoardForWinInFirstInnerBoard(Board board) {
        board.getBoardSpace()[0][0].getBoardSpace()[0][0].setXo(XO.X);
        board.getBoardSpace()[0][0].getBoardSpace()[0][1].setXo(XO.X);
        board.getBoardSpace()[0][0].getBoardSpace()[0][2].setXo(XO.X);

        return board;
    }
}