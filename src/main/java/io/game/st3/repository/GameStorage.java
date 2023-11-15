package io.game.st3.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.game.st3.domain.Game;

public class GameStorage {

    private static Map<UUID, Game> games;

    private static GameStorage instance;

    private GameStorage() {
        games = new HashMap<>();
    }

    public static GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }

    public Map<UUID, Game> getGames() {
        return games;
    }

    public Game getGameByUuid(UUID uuid) {
        return games.get(uuid);
    }

    public Game saveGame(UUID uuid, Game game) {
        return games.put(uuid, game);
    }
}
