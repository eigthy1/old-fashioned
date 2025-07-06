package io.github.eigthy1.chess.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLexer {
    public static final String TEST_CASES_FILE_PATH = "/io/github/eigthy1/chess/util/lexer_test_cases.json";
    private static final List<Case> TEST_CASES;

    static {
        try {
            TEST_CASES = new ObjectMapper().readValue(
                    TestLexer.class.getResourceAsStream(TEST_CASES_FILE_PATH),
                    new TypeReference<>() {}
            );
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Case {
        @SuppressWarnings("unused")
        private String move;
        @SuppressWarnings("unused")
        private List<String> lexemes;

        public String getMove() {
            return move;
        }

        public List<String> getLexemes() {
            return lexemes;
        }
    }

    public static List<Case> tokenization() {
        return TEST_CASES;
    }
    
    @ParameterizedTest
    @MethodSource("tokenization")
    public void tokenize(Case caze) {
        assertEquals(caze.getLexemes(), Lexer.tokenize(caze.getMove()));
    }
}
