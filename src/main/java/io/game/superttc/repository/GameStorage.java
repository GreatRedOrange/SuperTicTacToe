package io.game.superttc.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.game.superttc.domain.Game;
import io.game.superttc.domain.Player;

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

    public Game addGame(UUID uuid, Game game) {
        return games.put(uuid, game);
    }
}
