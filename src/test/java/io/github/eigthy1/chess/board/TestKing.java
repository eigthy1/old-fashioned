package io.github.eigthy1.chess.board;

import java.util.Set;
import java.util.stream.*;

import org.junit.jupiter.api.BeforeEach;

import io.github.eigthy1.chess.util.Squares;

public class TestKing extends TestPiece {
    @BeforeEach
    @Override
    public void setUp() {
        setPiece(new King(Piece.Color.BLACK));
    }

    public static Stream<Square> reachable() {
        return Squares.bulkBuild("b5 b6 b7 c5 c7 d5 d6 d7".split(" "));
    }

    public static Stream<Square> unreachable() {
        Set<Square> squares = TestQueen.reachable().collect(Collectors.toSet());
        squares.removeAll(reachable().collect(Collectors.toSet()));
        return squares.stream();
    }
}
