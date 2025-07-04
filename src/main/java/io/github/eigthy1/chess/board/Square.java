package io.github.eigthy1.chess.board;

import java.util.*;

public class Square {
    private final File file;
    private final Rank rank;

    public enum File {
        $A('a'), $B('b'), $C('c'), $D('d'), $E('e'), $F('f'), $G('g'), $H('h');

        private final Character symbol;

        File(Character symbol) {
            this.symbol = Objects.requireNonNull(symbol);
        }

        public Character getSymbol() {
            return symbol;
        }
    }

    public enum Rank {
        $1('1'), $2('2'), $3('3'), $4('4'), $5('5'), $6('6'), $7('7'), $8('8');

        private final Character symbol;

        Rank(Character symbol) {
            this.symbol = Objects.requireNonNull(symbol);
        }

        public Character getSymbol() {
            return symbol;
        }
    }

    public Square(String id) {
        this(
                Arrays.stream(File.values()).filter(f -> f.getSymbol().equals(id.charAt(0))).findFirst().orElse(null),
                Arrays.stream(Rank.values()).filter(r -> r.getSymbol().equals(id.charAt(1))).findFirst().orElse(null)
        );
    }

    private Square(File file, Rank rank) {
        this.file = Objects.requireNonNull(file);
        this.rank = Objects.requireNonNull(rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public String id() {
        return getFile().getSymbol().toString().concat(getRank().getSymbol().toString());
    }

    @Override
    public int hashCode() {
        return id().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass() && obj.hashCode() == hashCode();
    }

    public int diagonal(boolean asc) {
        Square origin = new Square(File.$A, Rank.$1);
        return fileDistance(origin, this)+(asc ? -1 : 1)*rankDistance(origin, this);
    }

    public static int chebyshevDistance(Square a, Square b) {
        return Math.max(fileDistance(a, b), rankDistance(a, b));
    }

    public static int fileDistance(Square a, Square b) {
        return Math.abs(b.getFile().getSymbol()-a.getFile().getSymbol());
    }

    public static int rankDistance(Square a, Square b) {
        return Math.abs(b.getRank().getSymbol()-a.getRank().getSymbol());
    }
}
