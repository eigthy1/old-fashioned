package io.github.eigthy1.chess;

import java.util.EmptyStackException;
import java.util.Stack;

public class Chess {
    public static final int BOARD_SIZE = 8;
    public static final char EMPTY_SQUARE = ' ';

    private final char[][] board;
    private boolean whiteTurn;
    private Integer moveNo;
    private final Stack<String> game = new Stack<>();

    public Chess() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        board[0] = new char[]{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
        board[1] = new char[]{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'};
        for(int i = 2; i < BOARD_SIZE-2; i++) {
            board[i] = new char[BOARD_SIZE];
            for(int j = 0; j < BOARD_SIZE; j++) board[i][j] = EMPTY_SQUARE;
        }
        board[BOARD_SIZE-2] = new char[]{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'};
        board[BOARD_SIZE-1] = new char[]{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        setWhiteTurn(true);
        setMoveNo(1);
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setMoveNo(Integer moveNo) {
        this.moveNo = moveNo;
    }

    public Integer getMoveNo() {
        return moveNo;
    }

    private Stack<String> getGame() {
        return game;
    }

    private String enPassant() {
        Square square;
        try {
            square = new Square(getGame().peek());
        } catch(EmptyStackException ignore) {
            return "-";
        }
        if(isWhiteTurn()) square.up();
        else square.down();
        return square.id();
    }

    public String fen() {
        StringBuilder notation = new StringBuilder();
        for(int i = BOARD_SIZE-1; i >= 0; i--) {
            int empties = 0;
            for(char square : board[i]) {
                if(square == EMPTY_SQUARE) empties++;
                else {
                    if(empties > 0) notation.append(empties);
                    notation.append(square);
                    empties = 0;
                }
            }
            if(empties > 0) notation.append(empties);
            notation.append('/');
        }
        notation.setCharAt(notation.length()-1, ' ');
        notation.append(isWhiteTurn() ? 'w' : 'b');
        notation.append(" KQkq ");
        notation.append(enPassant());
        notation.append(" 0 ");
        notation.append(getMoveNo());
        return notation.toString();
    }

    public void move(final String san) {
        Square start = new Square(san);
        for(int i = 2; i > 0; i--) {
            if(isWhiteTurn()) start.down();
            else start.up();
        }
        Square end = new Square(san);
        board[start.getRank().getValue()][start.getFile().getValue()] = EMPTY_SQUARE;
        board[end.getRank().getValue()][end.getFile().getValue()] = isWhiteTurn() ? 'P' : 'p';
        game.add(san);
        setWhiteTurn(!isWhiteTurn());
        if(isWhiteTurn()) setMoveNo(getMoveNo()+1);
    }
}
