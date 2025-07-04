package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import io.github.eigthy1.chess.util.Squares;

public class TestRook extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new Rook(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return Squares.bulkBuild("a6 b6 c1 c2 c3 c4 c5 c7 c8 d6 e6 f6 g6 h6".split(" "));
    }

    public static Stream<Square> unreachable() {
        return TestBishop.reachable();
    }
}
