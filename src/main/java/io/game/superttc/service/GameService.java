package io.game.superttc.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.game.superttc.domain.Game;
import io.game.superttc.domain.Player;
import io.game.superttc.domain.enums.GameStatus;
import io.game.superttc.repository.GameStorage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private GameBoardCreationService gameBoardCreationService;

    public Game createGame(Player player) {
        Game game = Game.builder()
                .uuid(UUID.randomUUID())
                .gameBoard(gameBoardCreationService.createBoard())
                .player1(player)
                .gameStatus(GameStatus.NEW)
                .build();

        GameStorage.getInstance().setGame(game.getUuid(), game);
        return game;
    }

    public Game addPlayerConcreteGame(Player player, UUID uuid) {
        Game game = GameStorage.getInstance().getGameByUuid(uuid);
        if (checkIfGameFull(game)) {
            throw new RuntimeException("Game is full");
        }
        game.setPlayer2(player);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game.getUuid(), game);
        return game;
    }

    public Game addPlayerToRandomGame(Player player) {
        Game game = GameStorage.getInstance().getGames().values()
                .stream()
                .filter(value -> value.getGameStatus().equals(GameStatus.NEW))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No vacant game at the moment"));

        game.setPlayer2(player);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game.getUuid(), game);
        return game;
    }

    private boolean checkIfGameFull(Game game) {
        return game.getGameStatus().equals(GameStatus.IN_PROGRESS);
    }
}
