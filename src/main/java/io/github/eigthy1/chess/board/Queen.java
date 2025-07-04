package io.github.eigthy1.chess.board;

public class Queen extends Piece {
    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @SuppressWarnings("unused")
    public static Character symbol() {
        return 'Q';
    }

    @Override
    public boolean go(Square from, Square to) {
        return new Rook(getColor()).go(from, to) || new Bishop(getColor()).go(from, to);
    }
}
