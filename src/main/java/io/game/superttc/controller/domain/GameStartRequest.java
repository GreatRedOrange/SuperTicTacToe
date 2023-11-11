package io.game.superttc.controller.domain;

import io.game.superttc.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStartRequest {

    private Player player;

}
