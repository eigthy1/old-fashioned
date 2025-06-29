package io.github.eigthy1.chess;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChess {
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String KINGS_PAWN_OPENING = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
    public static final String SICILIAN_DEFENSE = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
    public static final String[] TROUBLESOME_PRIEST = {
            "r2r2k1/5pbp/2p3p1/ppq5/2nNPPb1/1BP5/P1Q3PP/2B1RR1K w - a6 0 21",
            "r2r2k1/3b1pbp/2p3p1/ppq5/P1nNPP2/1BP4P/2Q3P1/2B1RR1K b - a3 0 22"
    };

    private Chess chess;

    private void play(String... moves) {
        for(String san : moves) chess.move(san);
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
    public void switchTurn() {
        play("e4");
        assertEquals(KINGS_PAWN_OPENING, chess.fen());
        play("c5");
        assertEquals(SICILIAN_DEFENSE, chess.fen());
    }

    @Test
    public void restartGame() {
        chess.load(TROUBLESOME_PRIEST[0]);
        assertEquals(TROUBLESOME_PRIEST[0], chess.fen());
        play("h3", "Bd7", "a4");
        assertEquals(TROUBLESOME_PRIEST[1], chess.fen());
    }
}
