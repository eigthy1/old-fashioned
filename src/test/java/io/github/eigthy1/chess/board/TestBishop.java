package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import io.github.eigthy1.chess.util.Squares;

public class TestBishop extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new Bishop(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return Squares.bulkBuild("a4 a8 b5 b7 d5 d7 e4 e8 f3 g2 h1".split(" "));
    }

    public static Stream<Square> unreachable() {
        return TestRook.reachable();
    }
}
