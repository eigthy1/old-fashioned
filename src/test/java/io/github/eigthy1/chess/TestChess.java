package io.github.eigthy1.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestChess {
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String KINGS_PAWN_OPENING = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
    public static final String SICILIAN_DEFENSE = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";

    private Chess chess;

    private void playChess(String... sans) {
        for(String san : sans) chess.move(san);
    }

    @BeforeEach
    public void setUp() {
        chess = new Chess();
    }

    @Test
    public void initState() {
        assertEquals(STARTING_POSITION, chess.fen());
    }

    @Test
    public void whiteMoves() {
        playChess("e4");
        assertEquals(KINGS_PAWN_OPENING, chess.fen());
    }

    @Test
    public void blackMoves() {
        playChess("e4", "c5");
        assertEquals(SICILIAN_DEFENSE, chess.fen());
    }
}
