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
        return Squares.bulkBuild(testSquares(Rook.symbol().toString(), POSITIVE_TEST));
    }

    public static Stream<Square> unreachable() {
        return TestBishop.reachable();
    }
}
