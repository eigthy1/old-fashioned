package io.github.eigthy1.chess.board;

public class Knight extends Piece {
    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @SuppressWarnings("unused")
    public static Character symbol() {
        return 'N';
    }

    @Override
    public boolean go(Square from, Square to) {
        return !new Queen(getColor()).go(from, to);
    }
}
