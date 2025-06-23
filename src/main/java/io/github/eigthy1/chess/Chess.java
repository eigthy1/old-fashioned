package io.github.eigthy1.chess;

import java.util.*;

public class Chess {
    public static final int BOARD_SIZE = 8;
    public static final char EMPTY_SQUARE = ' ';
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final Character[][] board;
    private boolean whiteTurn;
    private String castlingAvailable;
    private Integer halfmove;
    private Integer moveNo;
    private final Stack<String> game = new Stack<>();

    public Chess() {
        board = new Character[BOARD_SIZE][BOARD_SIZE];
        load(STARTING_POSITION);
    }

    private void setBoard(String board) {
        String[] ranks = board.split("/");
        for(int i = 0; i < BOARD_SIZE; i++) {
            Stack<String> rank = new Stack<>();
            rank.addAll(Arrays.asList(ranks[BOARD_SIZE-i-1].split("")));
            Collections.reverse(rank);
            int j = 0;
            while(!rank.isEmpty()) {
                Character square = rank.pop().charAt(0);
                if(Character.isDigit(square)) {
                    square--;
                    if(!square.equals('0')) rank.add(square.toString());
                    this.board[i][j] = EMPTY_SQUARE;
                } else this.board[i][j] = square;
                j++;
            }
        }
    }

    private String getBoard() {
        StringBuilder notation = new StringBuilder();
        for(int i = BOARD_SIZE-1; i >= 0; i--) {
            int empties = 0;
            for(Character square : board[i]) {
                if(square.equals(EMPTY_SQUARE)) empties++;
                else {
                    if(empties > 0) notation.append(empties);
                    notation.append(square);
                    empties = 0;
                }
            }
            if(empties > 0) notation.append(empties);
            notation.append('/');
        }
        notation.deleteCharAt(notation.length()-1);
        return notation.toString();
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setCastlingAvailable(String castlingAvailable) {
        this.castlingAvailable = castlingAvailable;
    }

    public String isCastlingAvailable() {
        return castlingAvailable;
    }

    public void setHalfmove(Integer halfmove) {
        this.halfmove = halfmove;
    }

    public Integer getHalfmove() {
        return halfmove;
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
        } catch(EmptyStackException | NullPointerException ignore) {
            return "-";
        }
        if(isWhiteTurn()) square.up();
        else square.down();
        return square.id();
    }

    public String fen() {
        return String.join(" ", new String[] {
                getBoard(),
                isWhiteTurn() ? "w" : "b",
                isCastlingAvailable(),
                enPassant(),
                getHalfmove().toString(),
                getMoveNo().toString()
        });
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

    public void load(String fen) {
        String[] fields = fen.split(" ");
        setBoard(fields[0]);
        setWhiteTurn(fields[1].equals("w"));
        setCastlingAvailable(fields[2]);
        if(!fields[3].equals("-")) {
            Square enPassant = new Square(fields[3]);
            enPassant.up();
            getGame().add(enPassant.id());
        }
        setHalfmove(Integer.valueOf(fields[4]));
        setMoveNo(Integer.valueOf(fields[5]));
    }
}
