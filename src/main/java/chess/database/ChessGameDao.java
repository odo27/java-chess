package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.square.Square;

public final class ChessGameDao {

    private final Database database;

    public ChessGameDao(final DatabaseName databaseName) {
        database = new Database(databaseName);
    }

    public void save(String gameId, ChessGame chessGame) {
        String query = "INSERT INTO Board (game_id, turn, piece_file, piece_rank, piece_type, piece_team) VALUES (?, ?, ?, ?, ?, ?)";
        Map<Square, Piece> board = chessGame.getBoard();
        for (Square square : board.keySet()) {
            try (Connection connection = database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                char fileValue = square.getFileValue();
                char rankValue = square.getRankValue();
                Piece piece = board.get(square);
                String pieceType = PieceName.from(piece);
                int pieceTeam = piece.getColor().getValue();
                preparedStatement.setLong(1, Integer.parseInt(gameId));
                preparedStatement.setInt(2, chessGame.getTurn());
                preparedStatement.setString(3, String.valueOf(fileValue));
                preparedStatement.setString(4, String.valueOf(rankValue));
                preparedStatement.setString(5, pieceType);
                preparedStatement.setInt(6, pieceTeam);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
