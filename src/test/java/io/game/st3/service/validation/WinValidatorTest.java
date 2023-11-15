package io.game.st3.service.validation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.game.st3.domain.Board;
import io.game.st3.domain.enums.XO;

@SpringBootTest
class WinValidatorTest {

    @InjectMocks
    private WinValidator winValidator;

    @Test
    public void testCheckForWinner_Row() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.X), new Board(null, XO.X) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) }
        };

        assertEquals(XO.X, winValidator.getWonXO(board));
    }

    @Test
    public void testCheckForWinner_Column() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) }
        };

        assertEquals(XO.X, winValidator.getWonXO(board));
    }

    @Test
    public void testCheckForWinner_Diagonal() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.EMPTY), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.X), new Board(null, XO.EMPTY) },
                { new Board(null, XO.EMPTY), new Board(null, XO.EMPTY), new Board(null, XO.X) }
        };

        assertEquals(XO.X, winValidator.getWonXO(board));
    }

    @Test
    public void testCheckForWinner_NoWinner() {
        Board[][] board = {
                { new Board(null, XO.X), new Board(null, XO.O), new Board(null, XO.X) },
                { new Board(null, XO.O), new Board(null, XO.X), new Board(null, XO.O) },
                { new Board(null, XO.O), new Board(null, XO.X), new Board(null, XO.O) }
        };

        assertEquals(XO.EMPTY, winValidator.getWonXO(board));
    }
}