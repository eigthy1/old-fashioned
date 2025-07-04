package io.github.eigthy1.chess.util;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static boolean isPawnMove(String san) {
        return san.toLowerCase().equals(san);
    }

    public static List<String> tokenize(String san) {
        List<String> lexemes = new ArrayList<>();
        if(!isPawnMove(san)) {
            lexemes.add(san.substring(0, 1));
            san = san.substring(1);
        }
        lexemes.add(san);
        return lexemes;
    }
}
