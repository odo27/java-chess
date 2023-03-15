package chess.domain.piece;

import chess.domain.piece.strategy.PawnDirection;
import chess.domain.piece.strategy.PawnMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("적이면 적으로 인식한다.")
    void is_enemy() {
        Piece whitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PawnDirection.UPPER));
        Piece blackPiece = new Pawn(Color.BLACK, new PawnMoveStrategy(PawnDirection.LOWER));
        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @Test
    @DisplayName("적이 아니면 적으로 인식하지 않는다.")
    void is_not_enemy() {
        Piece firstWhitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PawnDirection.UPPER));
        Piece secondWhitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PawnDirection.UPPER));
        assertThat(firstWhitePiece.isEnemy(secondWhitePiece)).isFalse();
    }
}