package io.github.eigthy1.chess.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import io.github.eigthy1.chess.util.Squares;

public class TestKnight extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new Knight(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return Squares.bulkBuild(testSquares(Knight.symbol().toString(), POSITIVE_TEST));
    }

    public static Stream<Square> unreachable() {
        return TestQueen.reachable();
    }
}
