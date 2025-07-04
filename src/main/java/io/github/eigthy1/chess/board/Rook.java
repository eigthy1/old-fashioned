package io.github.eigthy1.chess.board;

public class Rook extends Piece {
    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @SuppressWarnings("unused")
    public static Character symbol() {
        return 'R';
    }

    @Override
    public boolean go(Square from, Square to) {
        return to.getFile().equals(from.getFile()) || to.getRank().equals(from.getRank());
    }
}
