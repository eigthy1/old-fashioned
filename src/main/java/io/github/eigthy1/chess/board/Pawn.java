package io.github.eigthy1.chess.board;

public class Pawn extends Piece {
    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        if(target.fileDistance(getSquare()) > 0)
            throw new IllegalArgumentException();
        int d = getColor() == Color.WHITE ?
                target.getRank().getValue()-getSquare().getRank().getValue() :
                getSquare().getRank().getValue()-target.getRank().getValue();
        if(d != 1 && (d != 2 || getSquare().getRank().getSymbol() != (getColor() == Color.WHITE ? '2' : '7')))
            throw new IllegalArgumentException();
        setSquare(target);
    }
}
