package io.game.st3.controller.domain;

import io.game.st3.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStartRequest {

    private Player player;

}
