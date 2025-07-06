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

    @SuppressWarnings("DuplicateExpressions")
    public static List<String> tokenize(String s) {
        for(String regex : ANNOTATIONS) {
            Matcher matcher = Pattern.compile(regex.concat("$")).matcher(s);
            if(matcher.find()) {
                List<String> lexemes = tokenize(s.substring(0, matcher.start()));
                lexemes.add(matcher.group());
                return lexemes;
            }
        }
        for(String symbol : CHECKS) {
            if(s.endsWith(symbol)) {
                List<String> lexemes = tokenize(s.substring(0, s.length()-symbol.length()));
                lexemes.add(symbol);
                return lexemes;
            }
        }
        if(s.matches(String.join("", ".*", PROMOTION, "$"))) {
            List<String> lexemes = tokenize(s.substring(0, s.length()-2));
            lexemes.add(s.substring(s.length()-2));
            return lexemes;
        }
        if(Arrays.asList(CASTLINGS).contains(s))
            return new ArrayList<>(Collections.singletonList(s));
        if(s.contains(CAPTURE)) {
            List<String> lexemes = tokenize(s.replace(CAPTURE, "").substring(isPawnMove(s) ? 1 : 0));
            if(isPawnMove(s)) lexemes.addFirst(s.substring(0, 1));
            lexemes.add(lexemes.size()-1, CAPTURE);
            return lexemes;
        }
        if(!isPawnMove(s) && s.length() > 3) {
            List<String> lexemes = tokenize(s.substring(0, 1).concat(s.substring(s.length()-2)));
            lexemes.addAll(1, List.of(s.substring(1, s.length()-2).split("")));
            return lexemes;
        }
        if(!isPawnMove(s)) {
            List<String> lexemes = tokenize(s.substring(1));
            lexemes.addFirst(s.substring(0, 1));
            return lexemes;
        }
        return new ArrayList<>(Collections.singletonList(s));
    }
}
