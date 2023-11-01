package io.game.superttc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.Cell;
import io.game.superttc.domain.enums.CellStatus;

@SpringBootTest
class GameBoardCreationServiceTest {

    @Autowired
    private GameBoardCreationService gameBoardCreationService;

    @Test
    public void testGameBoardCreated() {
        Cell expectedCell = new Cell();
        expectedCell.setCellStatus(CellStatus.EMPTY);

        Board actualOuterBoard = gameBoardCreationService.createBoard();
        Board actualInnerBoard = (Board) actualOuterBoard.getBoardSpace()[0][0];
        Cell actualCell = (Cell) actualInnerBoard.getBoardSpace()[0][0];

        assertEquals(Board.class, actualInnerBoard.getClass());
        assertEquals(expectedCell, actualCell);

    }
}