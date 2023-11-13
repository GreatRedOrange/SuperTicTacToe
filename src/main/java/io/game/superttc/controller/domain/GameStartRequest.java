package io.game.superttc.controller.domain;

import io.game.superttc.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStartRequest {

    private Player player;

}
