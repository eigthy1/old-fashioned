package io.github.eigthy1.chess.board;

import io.github.eigthy1.chess.Playable;

public abstract class Piece implements Playable {
    private Color color;
    private Square square;

    public enum Color {WHITE, BLACK}

    public Piece(Color color, Square square) {
        setColor(color);
        setSquare(square);
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }
}
