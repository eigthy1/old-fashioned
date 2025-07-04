package io.github.eigthy1.chess.board;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    public static Character symbol() {
        return 'P';
    }

    @Override
    public boolean go(Square from, Square to) {
        char rank = from.getRank().getSymbol();
        boolean white = getColor().equals(Color.WHITE);
        int push = Square.rankDistance(from, to);
        if(push == 2 && !(white ? Square.Rank.$2 : Square.Rank.$7).getSymbol().equals(rank)) return false;
        if(Square.fileDistance(from, to) > 0 || push > 2) return false;
        rank += (char) (white ? push : -push);
        return to.getRank().getSymbol().equals(rank);
    }
}
