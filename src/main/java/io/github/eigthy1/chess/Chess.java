package io.github.eigthy1.chess;

import java.util.*;

import io.github.eigthy1.chess.board.*;

public class Chess {
    public static final int BOARD_SIZE = 8;
    public static final char EMPTY_SQUARE = ' ';
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private final Character[][] board;
    private Boolean whiteTurn;
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

    public void setWhiteTurn(Boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public Boolean isWhiteTurn() {
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
        return !getGame().isEmpty() && getGame().peek().equals(getGame().peek().toLowerCase()) ?
                new Square(getGame().peek().charAt(0)+(isWhiteTurn() ? "6" : "3")).id() : "-";
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
        Playable piece;
        Piece.Color color = isWhiteTurn() ? Piece.Color.WHITE : Piece.Color.BLACK;
        Square origin, target;
        if(san.equals(san.toLowerCase())) {
            origin = new Square(san.charAt(0)+(isWhiteTurn() ? "2" : "7"));
            piece = new Pawn(color, origin);
            target = new Square(san);
            board[target.getRank().getValue()][target.getFile().getValue()] = isWhiteTurn() ? 'P' : 'p';
        } else {
            origin = new Square("g4");
            piece = new Bishop(color, origin);
            target = new Square(san.substring(1));
            board[target.getRank().getValue()][target.getFile().getValue()] = isWhiteTurn() ? 'B' : 'b';
        }
        piece.go(target);
        board[origin.getRank().getValue()][origin.getFile().getValue()] = EMPTY_SQUARE;
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
            getGame().add(fields[3].charAt(0)+(isWhiteTurn() ? "5" : "4"));
        }
        setHalfmove(Integer.valueOf(fields[4]));
        setMoveNo(Integer.valueOf(fields[5]));
    }
}
