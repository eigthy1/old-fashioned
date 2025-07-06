package io.github.eigthy1.chess.util;

import java.util.List;
import java.util.stream.Stream;

import io.github.eigthy1.chess.board.Square;

public class Squares {
    public static Stream<Square> bulkBuild(List<String> elements) {
        return elements.stream().map(Square::new);
    }
}
