package io.game.superttc.service;

import java.util.Map;
import java.util.Optional;
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
    private GameStorage gameStorage;

    public Game createGame(Player player) {
        UUID uuid = UUID.randomUUID();

        Game game = Game.builder()
                .uuid(uuid)
                .gameBoard(gameBoardCreationService.createBoard())
                .player1(player)
                .gameStatus(GameStatus.NEW)
                .build();

        return GameStorage.getInstance().addGame(uuid, game);
    }

    public Game addPlayerConcreteGame(Player player, UUID uuid) {
        Game game = GameStorage.getInstance().getGameByUuid(uuid);
        if(checkIfGameFull(game)){
            throw new RuntimeException("Game is full");
        }
        game.setPlayer2(player);

        return GameStorage.getInstance().addGame(uuid, game);
    }

    public Game addPlayerToRandomGame(Player player) {
        Optional<Game> game = GameStorage.getInstance().getGames().values()
                .stream()
                .filter(value -> value.getPlayer2() == null)
                .findFirst();

        return game.orElseThrow(() -> new RuntimeException("No vacant game at the moment"));
    }

    private boolean checkIfGameFull(Game game) {
        return game.getPlayer2() != null;
    }
}
