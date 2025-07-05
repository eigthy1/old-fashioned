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

    static class Case {
        private String move;
        private List<String> lexemes;

        public String getMove() {
            return move;
        }

        public List<String> getLexemes() {
            return lexemes;
        }
    }

    public static List<Case> tokenization() throws IOException {
        return new ObjectMapper().readValue(
            TestLexer.class.getResourceAsStream(TEST_CASES_FILE_PATH),
            new TypeReference<List<Case>>() {}
        );
    }
    
    @ParameterizedTest
    @MethodSource("tokenization")
    public void tokenize(Case caze) {
        assertEquals(caze.getLexemes(), Lexer.tokenize(caze.getMove()));
    }
}
