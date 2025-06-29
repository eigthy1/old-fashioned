package io.github.eigthy1.chess.board;

public class Knight extends Piece {
    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        Queen queen = new Queen(getColor(), getSquare());
        try {
            queen.go(target);
        } catch(IllegalArgumentException ignore) {
            setSquare(target);
            return;
        }
        throw new IllegalArgumentException();
    }
}
