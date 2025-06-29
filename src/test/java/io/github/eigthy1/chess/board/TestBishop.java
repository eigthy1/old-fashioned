package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static io.github.eigthy1.chess.util.TestUtils.buildSquares;

public class TestBishop extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new Bishop(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return buildSquares("a4 a8 b5 b7 d5 d7 e4 e8 f3 g2 h1");
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        return TestRook.reachableSquares();
    }
}
