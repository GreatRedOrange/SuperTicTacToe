package io.game.st3.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import io.game.st3.domain.Board;
import io.game.st3.domain.enums.XO;
import io.game.st3.service.validation.WinValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WinService {

    private final WinValidator winValidator;

    public boolean checkGameWin(Board board) {
        applyBoardWin(board);

        Arrays.stream(board.getBoardSpace())
                .flatMap(Arrays::stream)
                .forEach(this::applyBoardWin);

        return board.getXo() == XO.X || board.getXo() == XO.O;
    }

    private void applyBoardWin(Board board) {
        XO boardResult = winValidator.getWonXO(board.getBoardSpace());
        board.setXo(boardResult);
    }
}
