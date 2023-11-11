package io.game.superttc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.enums.XO;

@SpringBootTest
class GameBoardCreationServiceTest {

    @Autowired
    private GameBoardCreationService gameBoardCreationService;

    @Test
    public void testGameBoardCreated() {
        Board actualOuterBoard = gameBoardCreationService.createBoard();
        Board actualInnerBoard1 = actualOuterBoard.getBoardSpace()[0][0];
        Board actualInnerBoard2 = actualOuterBoard.getBoardSpace()[0][1];
        XO actualXOValue = actualInnerBoard1.getBoardSpace()[0][0].getXo();

        assertEquals(Board.class, actualInnerBoard1.getClass());
        assertEquals(XO.EMPTY, actualXOValue);
        assertNotSame(actualInnerBoard1, actualInnerBoard2);
        assertNull(actualInnerBoard1.getBoardSpace()[0][0].getBoardSpace());
    }
}