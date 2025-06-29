package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static io.github.eigthy1.chess.util.TestUtils.buildSquares;

public class TestRook extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new Rook(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return buildSquares("a6 b6 c1 c2 c3 c4 c5 c7 c8 d6 e6 f6 g6 h6");
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        return TestBishop.reachableSquares();
    }
}
