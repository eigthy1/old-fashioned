package io.github.eigthy1.chess.board;

public class Bishop extends Piece {
    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        if(target.diagonal(true) != getSquare().diagonal(true) && target.diagonal(false) != getSquare().diagonal(false))
            throw new IllegalArgumentException();
        setSquare(target);
    }
}
