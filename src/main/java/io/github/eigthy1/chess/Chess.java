package io.github.eigthy1.chess;

import java.util.*;

import io.github.eigthy1.chess.board.*;
import io.github.eigthy1.chess.board.Piece.*;
import io.github.eigthy1.chess.board.Square.*;
import io.github.eigthy1.chess.util.Lexer;

public class Chess {
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String FEN_EMPTY_FIELD = "-";

    private Map<Square, Piece> board;
    private Boolean whiteTurn;
    private String castlingAvailable;
    private Integer halfmoveClock;
    private Integer fullmoveCounter;
    private final Stack<String> game = new Stack<>();

    public Chess() {
        load(STARTING_POSITION);
    }

    private void setBoard(String board) throws ReflectiveOperationException {
        this.board = new HashMap<>();
        char rank = Rank.$8.getSymbol();
        for(String line : board.split("/")) {
            char file = File.$A.getSymbol();
            for(Character c : line.toCharArray()) {
                if(Character.isDigit(c)) file += (char) (Character.getNumericValue(c)-1);
                else {
                    this.board.put(
                            new Square(String.valueOf(file).concat(String.valueOf(rank))),
                            Arrays.stream(Type.values()).filter(type -> {
                                try {
                                    Character symbol = (Character) type.getClazz().getMethod("symbol").invoke(null);
                                    return symbol.toString().equalsIgnoreCase(c.toString());
                                } catch(ReflectiveOperationException e) {
                                    throw new RuntimeException(e);
                                }
                            }).findFirst().orElseThrow().getClazz().getConstructor(Piece.Color.class).newInstance(
                                    Character.isUpperCase(c) ? Piece.Color.WHITE : Piece.Color.BLACK
                            )
                    );
                }
                file++;
            }
            rank--;
        }
    }

    private String getBoard() {
        StringBuilder board = new StringBuilder();
        for(char rank = Rank.$8.getSymbol(); rank >= Rank.$1.getSymbol(); rank--) {
            int empties = 0;
            for(char file = File.$A.getSymbol(); file <= File.$H.getSymbol(); file++) {
                Piece piece = this.board.get(new Square(String.valueOf(file).concat(String.valueOf(rank))));
                if(piece == null) empties++;
                else {
                    if(empties > 0) board.append(empties);
                    try {
                        board.append(piece.getSymbol());
                    } catch(ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                    empties = 0;
                }
            }
            if(empties > 0) board.append(empties);
            board.append('/');
        }
        board.deleteCharAt(board.length()-1);
        return board.toString();
    }

    public void setWhiteTurn(Boolean whiteTurn) {
        this.whiteTurn = Objects.requireNonNull(whiteTurn);
    }

    public Boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setCastlingAvailable(String castlingAvailable) {
        this.castlingAvailable = Objects.requireNonNull(castlingAvailable);
    }

    public String isCastlingAvailable() {
        return castlingAvailable;
    }

    public void setHalfmoveClock(Integer halfmoveClock) {
        this.halfmoveClock = Objects.requireNonNull(halfmoveClock);
    }

    public Integer getHalfmoveClock() {
        return halfmoveClock;
    }

    public void setFullmoveCounter(Integer fullmoveCounter) {
        this.fullmoveCounter = Objects.requireNonNull(fullmoveCounter);
    }

    public Integer getFullmoveCounter() {
        return fullmoveCounter;
    }

    private Stack<String> getGame() {
        return game;
    }

    private String enPassant() {
        if(getGame().isEmpty() || !Lexer.isPawnMove(getGame().peek())) return FEN_EMPTY_FIELD;
        return getGame().peek().substring(0, 1).concat((isWhiteTurn() ? Rank.$6 : Rank.$3).getSymbol().toString());
    }

    public String fen() {
        return String.join(" ",
                getBoard(),
                isWhiteTurn() ? "w" : "b",
                isCastlingAvailable(),
                enPassant(),
                getHalfmoveClock().toString(),
                getFullmoveCounter().toString()
        );
    }

    void play(String... moves) {
        for(String move : moves) play(move);
    }

    public void play(String move) {
        List<String> lexemes = Lexer.tokenize(move);
        Piece piece;
        Square from = null, to = new Square(lexemes.get(lexemes.size()-1));
        for(Map.Entry<Square, Piece> entry : board.entrySet()) {
            from = entry.getKey();
            piece = entry.getValue();
            if(!piece.getColor().equals(isWhiteTurn() ? Color.WHITE : Color.BLACK)) continue;
            try {
                String type = piece.getSymbol().toString().toUpperCase();
                if(!type.equals(Lexer.isPawnMove(move) ? Pawn.symbol().toString() : lexemes.get(0))) continue;
            } catch(ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
            if(!piece.go(from, to)) continue;
            break;
        }
        board.put(to, board.remove(from));
        getGame().add(move);
        setWhiteTurn(!isWhiteTurn());
        if(isWhiteTurn()) setFullmoveCounter(getFullmoveCounter()+1);
    }

    public void load(String fen) {
        String[] fields = fen.split(" ");
        try {
            setBoard(fields[0]);
        } catch(ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        setWhiteTurn(fields[1].equals("w"));
        setCastlingAvailable(fields[2]);
        if(!fields[3].equals(FEN_EMPTY_FIELD))
            getGame().add(fields[3].substring(0, 1).concat((isWhiteTurn() ? Rank.$5 : Rank.$4).getSymbol().toString()));
        setHalfmoveClock(Integer.valueOf(fields[4]));
        setFullmoveCounter(Integer.valueOf(fields[5]));
    }
}
