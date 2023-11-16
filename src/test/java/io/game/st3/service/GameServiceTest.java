package io.game.st3.service;

import io.game.st3.domain.*;
import io.game.st3.domain.enums.GameStatus;
import io.game.st3.domain.enums.XO;
import io.game.st3.repository.GameStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static io.game.st3.utils.TestConstants.*;
import static io.game.st3.utils.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GameServiceTest {

    @Autowired
    private GameBoardCreationService gameBoardCreationService;
    @InjectMocks
    private GameService gameService;

    private Board board;

    @BeforeEach
    void setup() {
        board = gameBoardCreationService.createBoard();
        GameStorage.getInstance().saveGame(NEW_GAME_ID, getNewGame(board));
        GameStorage.getInstance().saveGame(IN_PROGRESS_GAME_ID, getFullGame(board));
    }

    @Test
    void testCreateGame() {
        Game actualGame = gameService.createGame(getPlayer(FIRST_PLAYER_NAME, XO.X));

        assertEquals(UUID.class, actualGame.getUuid().getClass());
        assertEquals(board.getBoardSpace().length, actualGame.getGameBoard().getBoardSpace().length);
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(GameStatus.NEW, actualGame.getGameStatus());
    }

    @Test
    void testAddPlayerConcreteGame() {
        Game actualGame = gameService.addPlayerConcreteGame(getPlayer(SECOND_PLAYER_NAME, XO.O), IN_PROGRESS_GAME_ID);

        assertEquals(IN_PROGRESS_GAME_ID, actualGame.getUuid());
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(SECOND_PLAYER_NAME, actualGame.getPlayer2().getName());
        assertEquals(GameStatus.IN_PROGRESS, actualGame.getGameStatus());
    }

    @Test()
    void testAddPlayerConcreteGameThrowsExceptionWhenFull() {
        Exception ex = assertThrows(RuntimeException.class, () -> gameService.addPlayerConcreteGame(getPlayer(SECOND_PLAYER_NAME, XO.O), IN_PROGRESS_GAME_ID));
        assertEquals("Game is full", ex.getMessage());
    }

    @Test
    void testAddPlayerToRandomGame() {
        Game actualGame = gameService.addPlayerToRandomGame(getPlayer(SECOND_PLAYER_NAME, XO.O));

        assertEquals(IN_PROGRESS_GAME_ID, actualGame.getUuid());
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(SECOND_PLAYER_NAME, actualGame.getPlayer2().getName());
        assertEquals(GameStatus.IN_PROGRESS, actualGame.getGameStatus());
    }

    @Test
    void testAddPlayerToRandomGameThrowsExceptionWhenFull() {
        Exception ex = assertThrows(RuntimeException.class, () -> gameService.addPlayerToRandomGame(getPlayer(SECOND_PLAYER_NAME, XO.O)));
        assertEquals("No vacant game at the moment", ex.getMessage());
    }

    @Test
    void testMoveSuccess() {
        Player firstPlayer = getPlayer(FIRST_PLAYER_NAME, XO.X);
        Coordinate coordinate = getCoordinate(1, 1);
        Coordinates coordinates = getCoordinates(coordinate, coordinate);

        Game actualGame = gameService.move(IN_PROGRESS_GAME_ID, firstPlayer, coordinates);
        assertEquals(XO.X, actualGame.getGameBoard().getByCoordinate(coordinate).getByCoordinate(coordinate).getXo());
        assertEquals(XO.O, actualGame.getCurrentSymbol());
    }

    @Test
    void testMoveThrowsExceptionWhenNotPlayerTurn() {
        Player secondPlayer = getPlayer(SECOND_PLAYER_NAME, XO.O);
        Coordinate coordinate = getCoordinate(1, 1);
        Coordinates coordinates = getCoordinates(coordinate, coordinate);

        Exception ex = assertThrows(RuntimeException.class, () -> gameService.move(IN_PROGRESS_GAME_ID, secondPlayer, coordinates));
        assertEquals("It is not your turn", ex.getMessage());
    }

    @Test
    void testMoveThrowsExceptionWhenCellIsOccupied() {
        UUID uuid = UUID.randomUUID();
        Coordinate coordinate = getCoordinate(1, 1);

        Board board1 = gameBoardCreationService.createBoard();
        board1.getByCoordinate(coordinate).getByCoordinate(coordinate).setXo(XO.X);
        GameStorage.getInstance().saveGame(uuid, getNewGame(board1));

        Player firstPlayer = getPlayer(FIRST_PLAYER_NAME, XO.X);
        Coordinates coordinates = getCoordinates(coordinate, coordinate);

        Exception ex = assertThrows(RuntimeException.class, () -> gameService.move(uuid, firstPlayer, coordinates));
        assertEquals("This cell is occupied", ex.getMessage());

    }
}