package io.github.eigthy1.chess;

public class Chess {
    public static final int BOARD_SIZE = 8;
    public static final char EMPTY_SQUARE = ' ';
    public static final char FIRST_COL = 'a';
    public static final char FIRST_ROW = '1';

    private final char[][] board;
    private boolean whiteTurn;
    private int moveNo;
    private String passant;

    Chess() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        board[0] = new char[]{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
        board[1] = new char[]{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'};
        for(int i = 2; i < BOARD_SIZE-2; i++)
            board[i] = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        board[BOARD_SIZE-2] = new char[]{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'};
        board[BOARD_SIZE-1] = new char[]{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        setWhiteTurn(true);
        setMoveNo(1);
        setPassant("-");
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setMoveNo(int moveNo) {
        this.moveNo = moveNo;
    }

    public int getMoveNo() {
        return moveNo;
    }

    public void setPassant(String passant) {
        this.passant = passant;
    }

    public String getPassant() {
        return passant;
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
        notation.append(getPassant());
        notation.append(" 0 ");
        notation.append(getMoveNo());
        return notation.toString();
    }

    public void move(final String san) {
        int col = san.charAt(0)-FIRST_COL;
        int row = san.charAt(1)-FIRST_ROW;
        if(isWhiteTurn()) {
            board[1][col] = EMPTY_SQUARE;
            board[row][col] = 'P';
            setPassant(String.valueOf((char)(col+FIRST_COL))+(row));
        } else {
            board[BOARD_SIZE-2][col] = EMPTY_SQUARE;
            board[row][col] = 'p';
            setPassant(String.valueOf((char)(col+FIRST_COL))+(row+2));
        }
        whiteTurn = !isWhiteTurn();
        if(whiteTurn) moveNo++;
    }
}
