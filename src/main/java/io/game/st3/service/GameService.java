package io.game.st3.service;

import java.util.UUID;

import io.game.st3.domain.Coordinates;
import org.springframework.stereotype.Service;

import io.game.st3.domain.Game;
import io.game.st3.domain.Player;
import io.game.st3.domain.enums.GameStatus;
import io.game.st3.domain.enums.XO;
import io.game.st3.repository.GameStorage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameBoardCreationService gameBoardCreationService;

    public Game createGame(Player player1) {
        player1.setXo(XO.X);

        Game game = Game.builder()
                .uuid(UUID.randomUUID())
                .gameBoard(gameBoardCreationService.createBoard())
                .player1(player1)
                .currentSymbol(XO.X)
                .gameStatus(GameStatus.NEW)
                .build();

        GameStorage.getInstance().saveGame(game.getUuid(), game);
        return game;
    }

    public Game addPlayerConcreteGame(Player player2, UUID uuid) {
        player2.setXo(XO.O);

        Game game = GameStorage.getInstance().getGameByUuid(uuid);
        if (checkIfGameFull(game)) {
            throw new RuntimeException("Game is full");
        }
        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().saveGame(game.getUuid(), game);
        return game;
    }

    public Game addPlayerToRandomGame(Player player2) {
        player2.setXo(XO.O);

        Game game = GameStorage.getInstance().getGames().values()
                .stream()
                .filter(value -> value.getGameStatus().equals(GameStatus.NEW))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No vacant game at the moment"));

        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().saveGame(game.getUuid(), game);
        return game;
    }

    private boolean checkIfGameFull(Game game) {
        return game.getGameStatus().equals(GameStatus.IN_PROGRESS);
    }

    public Game move(UUID uuid, Player player, Coordinates coordinates) {
        Game game = GameStorage.getInstance().getGameByUuid(uuid);

        if (!player.getXo().equals(game.getCurrentSymbol())) {
            throw new RuntimeException("It is not your turn");
        }

        makeMove(player, coordinates, game);
        swapGameCurrentSymbol(game);
        GameStorage.getInstance().saveGame(uuid, game);

        return game;
    }

    private void makeMove(Player player, Coordinates coordinates, Game game) {
        if (isValidMove(coordinates, game)) {
            game.getGameBoard()
                    .getByCoordinate(coordinates.getOuterPos())
                    .getByCoordinate(coordinates.getInnerPos())
                    .setXo(player.getXo());
        }
    }

    private boolean isValidMove(Coordinates coordinates, Game game) {
        return game.getGameBoard()
                .getByCoordinate(coordinates.getOuterPos())
                .getByCoordinate(coordinates.getInnerPos())
                .getXo().equals(XO.EMPTY);
    }

    private void swapGameCurrentSymbol(Game game) {
        if (game.getCurrentSymbol().equals(XO.X)) {
            game.setCurrentSymbol(XO.O);
        } else {
            game.setCurrentSymbol(XO.X);
        }
    }
}
