package io.game.superttc.service.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import io.game.superttc.domain.Board;
import io.game.superttc.domain.enums.XO;

@SpringBootTest
class WinValidatorTest {

    @Autowired
    private WinValidator winValidator;

    @Test
    public void testCheckForWinner_Row() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.X), new Board(null, XO.X) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) }
        };

        assertEquals(XO.X, winValidator.checkForWinner(board));
    }

    @Test
    public void testCheckForWinner_Column() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) }
        };

        assertEquals(XO.X, winValidator.checkForWinner(board));
    }

    @Test
    public void testCheckForWinner_Diagonal() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.X), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.X) }
        };

        assertEquals(XO.X, winValidator.checkForWinner(board));
    }

    @Test
    public void testCheckForWinner_NoWinner() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.O), new Board(null, XO.X) },
                { new Board(null, XO.O), new Board(null, XO.X), new Board(null, XO.O) },
                { new Board(null, XO.O), new Board(null, XO.X), new Board(null, XO.O) }
        };

        assertEquals(XO.EMPTY, winValidator.checkForWinner(board));
    }
}