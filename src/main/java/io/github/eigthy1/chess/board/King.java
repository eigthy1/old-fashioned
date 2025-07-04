package io.github.eigthy1.chess.board;

public class King extends Piece {
    public King(Color color) {
        super(Type.KING, color);
    }

    @SuppressWarnings("unused")
    public static Character symbol() {
        return 'K';
    }

    @Override
    public boolean go(Square from, Square to) {
        return Square.chebyshevDistance(from, to) == 1;
    }
}
