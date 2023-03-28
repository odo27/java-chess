package chess.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoadGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao(DatabaseName.TEST);
    private final LoadGameDao loadGameDao = new LoadGameDao(DatabaseName.TEST);
    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database(DatabaseName.TEST);
        Connection connection = database.getConnection();
        assert connection != null;
        ChessTest.clearAll(connection);
        ChessTest.createMockUser(connection, "odo27", "mangmoong");
        ChessTest.createMockGame(connection, "100", "odo27");
    }

    @Test
    void get_last_turn_by_id() {
        ChessGame chessGame = new ChessGame(Board.create(), new Turn());
        chessGameDao.save("100", chessGame);
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        chessGameDao.save("100", chessGame);
        chessGame.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE));
        chessGameDao.save("100", chessGame);
        assertThat(loadGameDao.getLastTurnById("100")).isEqualTo(2);
    }

    @Test
    void get_game_by_id() {
        ChessGame chessGame = new ChessGame();
        chessGameDao.save("100", chessGame);
        ChessGame newChessGame = loadGameDao.getGameById("100", 0);
        assertThat(newChessGame.getBoard()).hasSize(32);
    }

    @Test
    void get_last_game_id() {
        Connection connection = database.getConnection();
        ChessTest.createMockGame(connection, "101", "odo27");
        ChessTest.createMockGame(connection, "102", "odo27");
        assertThat(loadGameDao.getLastGameId("odo27")).isEqualTo("102");
    }
}
