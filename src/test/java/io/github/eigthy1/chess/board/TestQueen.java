package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

public class TestQueen extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new Queen(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return Stream.concat(TestRook.reachable(), TestBishop.reachable());
    }

    public static Stream<Square> unreachable() {
        return TestKnight.reachable();
    }
}
