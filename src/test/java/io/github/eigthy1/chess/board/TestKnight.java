package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static io.github.eigthy1.chess.util.TestUtils.buildSquares;

public class TestKnight extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new Knight(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return buildSquares("a5 a7 b4 b8 d4 d8 e5 e7");
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        return TestQueen.reachableSquares();
    }
}
