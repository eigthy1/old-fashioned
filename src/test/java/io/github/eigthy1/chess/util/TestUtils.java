package io.github.eigthy1.chess.util;

import java.util.Arrays;
import java.util.stream.Stream;

import io.github.eigthy1.chess.board.Square;

public class TestUtils {
    public static Stream<Square> buildSquares(String list) {
        return Arrays.stream(list.split(" ")).map(Square::new);
    }
}
