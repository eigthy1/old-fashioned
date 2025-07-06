package io.github.eigthy1.chess.board;

import java.util.Set;
import java.util.stream.*;

import org.junit.jupiter.api.BeforeEach;

public class TestKing extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new King(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return TestQueen.reachable().filter(target -> Square.chebyshevDistance(new Square(ORIGIN), target) == 1);
    }

    public static Stream<Square> unreachable() {
        Set<Square> squares = TestQueen.reachable().collect(Collectors.toSet());
        squares.removeAll(reachable().collect(Collectors.toSet()));
        return squares.stream();
    }
}
