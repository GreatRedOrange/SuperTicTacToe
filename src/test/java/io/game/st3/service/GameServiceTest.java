package io.game.st3.service;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import static io.game.st3.utils.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.game.st3.domain.Game;
import io.game.st3.domain.enums.GameStatus;
import io.game.st3.repository.GameStorage;

import static io.game.st3.utils.TestConstants.FIRST_PLAYER_NAME;
import static io.game.st3.utils.TestConstants.ID;
import static io.game.st3.utils.TestConstants.SECOND_PLAYER_NAME;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GameServiceTest {

    @Mock
    private GameBoardCreationService gameBoardCreationService;
    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setup() {
        GameStorage.getInstance().saveGame(ID, getNewGame());
        when(gameBoardCreationService.createBoard()).thenReturn(getNullGameBoard());
    }

    @Test
    void testCreateGame() {
        Game actualGame = gameService.createGame(getPlayer(FIRST_PLAYER_NAME));

        assertEquals(UUID.class, actualGame.getUuid().getClass());
        assertEquals(getNullGameBoard().getBoardSpace().length, actualGame.getGameBoard().getBoardSpace().length);
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(GameStatus.NEW, actualGame.getGameStatus());
    }

    @Test
    void testAddPlayerConcreteGame() {
        Game actualGame = gameService.addPlayerConcreteGame(getPlayer(SECOND_PLAYER_NAME), ID);

        assertEquals(ID, actualGame.getUuid());
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(SECOND_PLAYER_NAME, actualGame.getPlayer2().getName());
        assertEquals(GameStatus.IN_PROGRESS, actualGame.getGameStatus());
    }

    @Test()
    void testAddPlayerConcreteGameThrowsExceptionWhenFull() {
        GameStorage.getInstance().saveGame(ID, getFullGame());

        Exception ex = assertThrows(RuntimeException.class, () -> gameService.addPlayerConcreteGame(getPlayer(SECOND_PLAYER_NAME), ID));
        assertEquals("Game is full", ex.getMessage());
    }

    @Test
    void testAddPlayerToRandomGame() {
        Game actualGame = gameService.addPlayerToRandomGame(getPlayer(SECOND_PLAYER_NAME));

        assertEquals(ID, actualGame.getUuid());
        assertEquals(FIRST_PLAYER_NAME, actualGame.getPlayer1().getName());
        assertEquals(SECOND_PLAYER_NAME, actualGame.getPlayer2().getName());
        assertEquals(GameStatus.IN_PROGRESS, actualGame.getGameStatus());
    }

    @Test
    void testAddPlayerToRandomGameThrowsExceptionWhenFull() {
        GameStorage.getInstance().saveGame(ID, getFullGame());

        Exception ex = assertThrows(RuntimeException.class, () -> gameService.addPlayerToRandomGame(getPlayer(SECOND_PLAYER_NAME)));
        assertEquals("No vacant game at the moment", ex.getMessage());
    }
}