package io.github.eigthy1.chess.board;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static io.github.eigthy1.chess.util.TestUtils.buildSquares;

public class TestKing extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        piece = new King(Piece.Color.BLACK, new Square(ORIGIN_SQUARE));
    }

    @SuppressWarnings("unused")
    static Stream<Square> reachableSquares() {
        return buildSquares("b5 b6 b7 c5 c7 d5 d6 d7");
    }

    @SuppressWarnings("unused")
    static Stream<Square> unreachableSquares() {
        Set<Square> squares = TestQueen.reachableSquares().collect(Collectors.toSet());
        squares.removeAll(reachableSquares().collect(Collectors.toSet()));
        return squares.stream();
    }
}
