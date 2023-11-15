package io.game.st3.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.game.st3.controller.domain.ConnectRequest;
import io.game.st3.controller.domain.GameStartRequest;
import io.game.st3.domain.Game;
import io.game.st3.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/st3")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public Game createGame(@RequestBody GameStartRequest gameStartRequest) {
        log.trace(String.format("Game created with player: %s", gameStartRequest.getPlayer()));
        System.out.println(gameStartRequest.getPlayer().getName());
        return gameService.createGame(gameStartRequest.getPlayer());
    }

    @PostMapping("/connect")
    public Game connectToGame(@RequestBody ConnectRequest connectRequest) {
        if (connectRequest.getUuid() != null) {
            log.trace(String.format("Player: %s was connected to gameId: %s", connectRequest.getPlayer().getName(), connectRequest.getUuid()));
            return gameService.addPlayerConcreteGame(connectRequest.getPlayer(), connectRequest.getUuid());
        } else {
            log.trace(String.format("Player: %s looking for a random game", connectRequest.getPlayer().getName()));
            return gameService.addPlayerToRandomGame(connectRequest.getPlayer());
        }
    }
}
