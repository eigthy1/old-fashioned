package io.github.eigthy1.chess.board;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @SuppressWarnings("unused")
    public static Character symbol() {
        return 'B';
    }

    @Override
    public boolean go(Square from, Square to) {
        return to.diagonal(true) == from.diagonal(true) || to.diagonal(false) == from.diagonal(false);
    }
}
