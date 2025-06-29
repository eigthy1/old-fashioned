package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestPiece {
    public static final String ORIGIN_SQUARE = "c6";

    Piece piece;

    @SuppressWarnings("unused")
    public abstract void setUp();

    @ParameterizedTest
    @MethodSource("reachableSquares")
    public void legalMove(Square square) {
        piece.go(square);
        assertSame(square, piece.getSquare());
    }

    @ParameterizedTest
    @MethodSource("unreachableSquares")
    public void illegalMove(Square square) {
        assertThrows(IllegalArgumentException.class, () -> piece.go(square));
    }

    static Stream<Square> reachableSquares() {
        throw new UnsupportedOperationException();
    }

    static Stream<Square> unreachableSquares() {
        throw new UnsupportedOperationException();
    }
}
