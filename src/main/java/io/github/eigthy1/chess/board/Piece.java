package io.github.eigthy1.chess.board;

import java.util.Objects;

import io.github.eigthy1.chess.Playable;

public abstract class Piece implements Playable {
    private final Type type;
    private final Color color;

    public enum Type {
        PAWN(Pawn.class), KNIGHT(Knight.class), BISHOP(Bishop.class), ROOK(Rook.class), QUEEN(Queen.class), KING(King.class);

        private final Class<? extends Piece> clazz;

        Type(Class<? extends Piece> clazz) {
            this.clazz = Objects.requireNonNull(clazz);
        }

        public Class<? extends Piece> getClazz() {
            return clazz;
        }
    }

    public enum Color {WHITE, BLACK}

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Character getSymbol() throws ReflectiveOperationException {
        Character symbol = (Character) getType().getClazz().getMethod("symbol").invoke(null);
        return getColor().equals(Color.WHITE) ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
    }

    public static Character symbol() {
        throw new UnsupportedOperationException();
    }
}
