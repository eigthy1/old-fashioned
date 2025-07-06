package io.github.eigthy1.chess.board;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestPiece {
    public static final String ORIGIN = "c6";
    public static final String POSITIVE_TEST = "+";
    public static final String NEGATIVE_TEST = "-";
    public static final String TEST_SQUARES_FILE_PATH = "/io/github/eigthy1/chess/board/piece_test_squares.json";
    private static final Map<String, Map<String, List<String>>> TEST_SQUARES;

    static {
        try {
            TEST_SQUARES = new ObjectMapper().readValue(
                TestPiece.class.getResourceAsStream(TEST_SQUARES_FILE_PATH),
                new TypeReference<Map<String, Map<String, List<String>>>>() {}
            );
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    Piece piece;

    static List<String> testSquares(String piece, String polarity) {
        return TEST_SQUARES.get(piece.toLowerCase()).get(polarity.toLowerCase());
    }

    @SuppressWarnings("unused")
    public abstract void setUp();

    @ParameterizedTest
    @MethodSource("reachable")
    public void legalMove(Square target) {
        assertTrue(getPiece().go(new Square(ORIGIN), target));
    }

    @ParameterizedTest
    @MethodSource("unreachable")
    public void illegalMove(Square target) {
        assertFalse(getPiece().go(new Square(ORIGIN), target));
    }

    public static Stream<Square> reachable() {
        throw new UnsupportedOperationException();
    }

    public static Stream<Square> unreachable() {
        throw new UnsupportedOperationException();
    }

    void setPiece(Piece piece) {
        this.piece = Objects.requireNonNull(piece);
    }

    private Piece getPiece() {
        return piece;
    }
}
