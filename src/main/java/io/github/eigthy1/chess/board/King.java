package io.github.eigthy1.chess.board;

public class King extends Piece {
    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        Queen queen = new Queen(getColor(), getSquare());
        try {
            queen.go(target);
            if(getSquare().fileDistance(target) != 1 && getSquare().rankDistance(target) != 1)
                throw new IllegalArgumentException();
            setSquare(target);
        } catch(IllegalArgumentException ignore) {
            throw new IllegalArgumentException();
        }
    }
}
