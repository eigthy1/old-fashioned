package io.github.eigthy1.chess.util;

import java.util.*;
import java.util.regex.*;

public class Lexer {
    public static final String[] CASTLINGS = {"O-O", "O-O-O"};
    public static final String[] ANNOTATIONS = {"=", "\\+-", "-\\+", "[!?]{1,2}"};
    public static final String[] CHECKS = {"+", "++", "#"};
    public static final String PROMOTION = "=[QRBN]";
    public static final String CAPTURE = "x";

    public static boolean isPawnMove(String san) {
        return san.toLowerCase().equals(san);
    }

    public static List<String> tokenize(String s) {
        for(String regex : ANNOTATIONS) {
            Matcher matcher = Pattern.compile(regex.concat("$")).matcher(s);
            if(matcher.find()) {
                List<String> lexems = tokenize(s.substring(0, matcher.start()));
                lexems.add(matcher.group());
                return lexems;
            }
        }
        for(String symbol : CHECKS) {
            if(s.endsWith(symbol)) {
                List<String> lexems = tokenize(s.substring(0, s.length()-symbol.length()));
                lexems.add(symbol);
                return lexems;
            }
        }
        if(s.matches(String.join("", ".*", PROMOTION, "$"))) {
            List<String> lexems = tokenize(s.substring(0, s.length()-2));
            lexems.add(s.substring(s.length()-2));
            return lexems;
        }
        if(Arrays.asList(CASTLINGS).contains(s))
            return new ArrayList<>(Collections.singletonList(s));
        if(s.contains(CAPTURE)) {
            List<String> lexemes = tokenize(s.replace(CAPTURE, "").substring(isPawnMove(s) ? 1 : 0));
            if(isPawnMove(s)) lexemes.add(0, s.substring(0, 1));
            lexemes.add(lexemes.size()-1, CAPTURE);
            return lexemes;
        }
        if(!isPawnMove(s) && s.length() > 3) {
            List<String> lexemes = tokenize(s.substring(0, 1).concat(s.substring(s.length()-2)));
            lexemes.add(1, s.substring(1, s.length()-2));
            return lexemes;
        }
        if(!isPawnMove(s)) {
            List<String> lexemes = tokenize(s.substring(1));
            lexemes.add(0, s.substring(0, 1));
            return lexemes;
        }
        return new ArrayList<>(Collections.singletonList(s));
    }
}
