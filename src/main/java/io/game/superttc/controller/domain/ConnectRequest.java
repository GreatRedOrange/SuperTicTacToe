package io.game.superttc.controller.domain;

import java.util.UUID;

import io.game.superttc.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectRequest {

    private UUID uuid;
    private Player player;

}
