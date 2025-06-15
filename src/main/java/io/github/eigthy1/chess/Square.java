package io.github.eigthy1.chess;

public class Square {
    private File file;
    private Rank rank;

    public enum File {
        A(0, 'a'),
        B(1, 'b'),
        C(2, 'c'),
        D(3, 'd'),
        E(4, 'e'),
        F(5, 'f'),
        G(6, 'g'),
        H(7, 'h');

        private final int value;
        private final char symbol;

        File(int value, char symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        public int getValue() {
            return value;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    public enum Rank {
        $1(0, '1'),
        $2(1, '2'),
        $3(2, '3'),
        $4(3, '4'),
        $5(4, '5'),
        $6(5, '6'),
        $7(6, '7'),
        $8(7, '8');

        private final int value;
        private final char symbol;

        Rank(int value, char symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        public int getValue() {
            return value;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    public Square(String id) {
        for(File file : File.values())
            if(file.getSymbol() == id.charAt(0)) setFile(file);
        setFile(getFile());
        for(Rank rank : Rank.values())
            if(rank.getSymbol() == id.charAt(1)) setRank(rank);
        setRank(getRank());
    }

    public String id() {
        return Character.toString(getFile().getSymbol())+getRank().getSymbol();
    }

    private void setFile(File file) {
        if(file == null) throw new NullPointerException();
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    private void setRank(Rank rank) {
        if(rank == null) throw new NullPointerException();
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    void up() {
        for(Rank rank : Rank.values())
            if(rank.getValue() == getRank().getValue()+1) {
                setRank(rank);
                return;
            }
    }

    void down() {
        for(Rank rank : Rank.values())
            if(rank.getValue() == getRank().getValue()-1) {
                setRank(rank);
                return;
            }
    }
}
