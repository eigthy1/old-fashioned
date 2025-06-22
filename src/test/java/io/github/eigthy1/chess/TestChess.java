package io.github.eigthy1.chess;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestChess {
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String KINGS_PAWN_OPENING = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
    public static final String SICILIAN_DEFENSE = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
    public static final String MARSHALLS_QUEEN_SACRIFICE = "5rk1/pp4pp/4p3/2R3Q1/3n4/6qr/P1P2PPP/5RK1 w - - 2 24";

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
    public void whiteMoves() {
        play("e4");
        assertEquals(KINGS_PAWN_OPENING, chess.fen());
    }

    @Test
    public void blackMoves() {
        play("e4", "c5");
        assertEquals(SICILIAN_DEFENSE, chess.fen());
    }

    @Test
    public void restartGame() {
        chess.load(MARSHALLS_QUEEN_SACRIFICE);
        assertEquals(MARSHALLS_QUEEN_SACRIFICE, chess.fen());
    }
}
