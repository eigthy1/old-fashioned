package io.github.eigthy1.chess.board;

public class Queen extends Piece {
    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public void go(Square target) {
        Piece[] queen = new Piece[] {new Rook(getColor(), getSquare()), new Bishop(getColor(), getSquare())};
        for(Piece piece : queen) {
            try {
                piece.go(target);
                setSquare(target);
                return;
            } catch(IllegalArgumentException ignore) {}
        }
        throw new IllegalArgumentException();
    }
}
