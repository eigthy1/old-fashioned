package io.github.eigthy1.chess.board;

public class Rook extends Piece {
    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        if(target.getFile() != getSquare().getFile() && target.getRank() != getSquare().getRank())
            throw new IllegalArgumentException();
        setSquare(target);
    }
}
