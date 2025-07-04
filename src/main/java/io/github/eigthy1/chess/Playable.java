package io.github.eigthy1.chess;

import io.github.eigthy1.chess.board.Square;

public interface Playable {
    boolean go(Square from, Square to);
}
