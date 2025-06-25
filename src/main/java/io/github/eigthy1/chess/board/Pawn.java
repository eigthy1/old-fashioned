package io.github.eigthy1.chess.board;

public class Pawn extends Piece {
    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        Square origin = getSquare();
        if(getColor() == Color.WHITE) {
            if(target.getFile() == origin.getFile()) {
                int d = target.getRank().getValue()-origin.getRank().getValue();
                if(d != 1 && (d != 2 && origin.getRank().getSymbol() == '2'))
                    throw new IllegalArgumentException();
            }
        } else {
            if(target.getFile() == origin.getFile()) {
                int d = origin.getRank().getValue()-target.getRank().getValue();
                if(d != 1 && (d != 2 && origin.getRank().getSymbol() == '7'))
                    throw new IllegalArgumentException();
            }
        }
        setSquare(target);
    }
}
