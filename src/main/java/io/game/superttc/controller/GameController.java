package io.game.superttc.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.game.superttc.controller.domain.ConnectRequest;
import io.game.superttc.controller.domain.GameStartRequest;
import io.game.superttc.domain.Game;
import io.game.superttc.service.GameService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/st3")
public class GameController {

    private GameService gameService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public Game createGame(@RequestBody GameStartRequest gameStartRequest) {
        log.trace(String.format("Game created with player: %s", gameStartRequest.getPlayer()));
        return gameService.createGame(gameStartRequest.getPlayer());
    }

    @PostMapping("/connect")
    public Game connectToGame(@RequestBody ConnectRequest connectRequest) {
        if (connectRequest.getUuid() != null) {
            log.trace(String.format("Player: %s was connected to gameId: %s", connectRequest.getPlayer(), connectRequest.getUuid()));
            return gameService.addPlayerConcreteGame(connectRequest.getPlayer(), connectRequest.getUuid());
        } else {
            log.trace(String.format("Player: %s looking for a random game", connectRequest.getPlayer()));
            return gameService.addPlayerToRandomGame(connectRequest.getPlayer());
        }
    }
}
