package io.github.eigthy1.chess.board;

import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestPiece {
    public static final String ORIGIN = "c6";

    Piece piece;

    @SuppressWarnings("unused")
    public abstract void setUp();

    @ParameterizedTest
    @MethodSource("reachable")
    public void legalMove(Square target) {
        assertTrue(getPiece().go(new Square(ORIGIN), target));
    }

    @ParameterizedTest
    @MethodSource("unreachable")
    public void illegalMove(Square target) {
        assertFalse(getPiece().go(new Square(ORIGIN), target));
    }

    public static Stream<Square> reachable() {
        throw new UnsupportedOperationException();
    }

    public static Stream<Square> unreachable() {
        throw new UnsupportedOperationException();
    }

    void setPiece(Piece piece) {
        this.piece = Objects.requireNonNull(piece);
    }

    private Piece getPiece() {
        return piece;
    }
}
