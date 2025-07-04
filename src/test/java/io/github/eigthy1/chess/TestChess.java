package io.github.eigthy1.chess;

import java.util.Objects;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChess {
    public static final String[] SICILIAN_DEFENSE = {
            "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1",
            "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
    };
    public static final String[] TROUBLESOME_PRIEST = {
            "r2r2k1/5pbp/2p3p1/ppq5/2nNPPb1/1BP5/P1Q3PP/2B1RR1K w - a6 0 21",
            "r2r2k1/3b1pbp/2p3p1/ppq5/P1nNPP2/1BP4P/2Q3P1/2B1RR1K b - a3 0 22"
    };

    private Chess chess;

    @BeforeEach
    public void setUp() {
        setChess(new Chess());
    }

    @Test
    public void initState() {
        assertEquals(Chess.STARTING_POSITION, getChess().fen());
    }

    @Test
    public void switchTurn() {
        getChess().play("e4");
        assertEquals(SICILIAN_DEFENSE[0], getChess().fen());
        getChess().play("c5");
        assertEquals(SICILIAN_DEFENSE[1], getChess().fen());
    }

    @Test
    public void restartGame() {
        getChess().load(TROUBLESOME_PRIEST[0]);
        assertEquals(TROUBLESOME_PRIEST[0], getChess().fen());
        getChess().play("h3", "Bd7", "a4");
        assertEquals(TROUBLESOME_PRIEST[1], getChess().fen());
    }

    private void setChess(Chess chess) {
        this.chess = Objects.requireNonNull(chess);
    }

    private Chess getChess() {
        return chess;
    }
}
