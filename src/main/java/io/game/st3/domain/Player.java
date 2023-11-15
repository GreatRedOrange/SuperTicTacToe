package io.game.st3.domain;

import io.game.st3.domain.enums.XO;
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
