package io.game.superttc.domain;

import io.game.superttc.domain.enums.XO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private String name;
    XO xo;

}
