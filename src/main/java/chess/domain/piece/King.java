package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class King extends Piece {

    public King(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {

        return null;
    }
}