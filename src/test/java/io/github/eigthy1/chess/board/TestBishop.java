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
        return Squares.bulkBuild(testSquares(Bishop.symbol().toString(), POSITIVE_TEST));
    }

    public static Stream<Square> unreachable() {
        return TestRook.reachable();
    }
}
