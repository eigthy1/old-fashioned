package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static io.github.eigthy1.chess.util.TestUtils.buildSquares;

public class TestPawn extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new Pawn(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return buildSquares("c5");
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        return buildSquares("b6 c7 d6");
    }
}
