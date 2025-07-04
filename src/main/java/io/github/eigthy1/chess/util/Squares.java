package io.github.eigthy1.chess.util;

import java.util.Arrays;
import java.util.stream.Stream;

import io.github.eigthy1.chess.board.Square;

public class Squares {
    public static Stream<Square> bulkBuild(String[] elements) {
        return Arrays.stream(elements).map(Square::new);
    }
}
