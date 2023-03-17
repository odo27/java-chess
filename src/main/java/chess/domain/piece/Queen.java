package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Queen extends Piece {

    private final MoveStrategy subMoveStrategy;

    public Queen(final Color color, final MoveStrategy moveStrategy, final MoveStrategy subMoveStrategy) {
        super(color, moveStrategy);
        this.subMoveStrategy = subMoveStrategy;
    }

    @Override
    public Direction findDirection(Square current, Square destination) {

        return null;
    }
}