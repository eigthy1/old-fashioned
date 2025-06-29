package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

public class TestQueen extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new Queen(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return Stream.concat(TestRook.reachableSquares(), TestBishop.reachableSquares());
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        return TestKnight.reachableSquares();
    }
}
